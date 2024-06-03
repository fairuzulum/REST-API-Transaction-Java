package com.transaction.service.impl;

import com.transaction.constan.UserRole;
import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.request.AuthRequestCustomer;
import com.transaction.dto.response.LoginResponse;
import com.transaction.dto.response.RegisterResponse;
import com.transaction.dto.response.RegisterResponseCustomer;
import com.transaction.entity.Customer;
import com.transaction.entity.Role;
import com.transaction.entity.UserAccount;
import com.transaction.repository.UserAccountRepository;
import com.transaction.service.AuthService;
import com.transaction.service.CustomerService;
import com.transaction.service.JwtService;
import com.transaction.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final CustomerService customerService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponseCustomer register(AuthRequestCustomer request) {
        Optional<UserAccount> currentUser = userAccountRepository.findByUsername(request.getUsername());

        if(currentUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.OK, "user exist");
        }

        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(List.of(customer))
                .isNabled(true)
                .build();
        userAccountRepository.saveAndFlush(account);

        Customer addCustomer = Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .isMember(true)
                .userAccount(account)
                .build();

        customerService.create(addCustomer);

        return RegisterResponseCustomer.builder()
                .name(addCustomer.getName())
                .phone(addCustomer.getPhone())
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public RegisterResponse registerAdmin(AuthRequest request) {
//        Optional<UserAccount> currentUser = userAccountRepository.findByUsername(request.getUsername());
//
//        if(currentUser.isPresent()){
//            throw new ResponseStatusException(HttpStatus.OK, "user exist");
//        }
//
//        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
//        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
//
//        UserAccount account = UserAccount.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(List.of(admin, customer))
//                .isNabled(true)
//                .build();
//        userAccountRepository.saveAndFlush(account);
//
//        return RegisterResponse.builder()
//                .username(account.getUsername())
//                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
//                .build();
//
//    }

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initAdmin() {
        Optional<UserAccount> currentUser = userAccountRepository.findByUsername("admin");
        if (currentUser.isPresent()) {
            return;
        }

        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);

        UserAccount account = UserAccount.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .role(List.of(admin, customer))
                .isNabled(true)
                .build();

        userAccountRepository.save(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(AuthRequest request) {
        Optional<UserAccount> username = userAccountRepository.findByUsername(request.getUsername());
        if(username.isEmpty()){
            throw new ResponseStatusException(HttpStatus.OK, "user not exist");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authentication);
        UserAccount user = (UserAccount) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
