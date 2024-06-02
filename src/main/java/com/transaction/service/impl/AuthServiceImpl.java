package com.transaction.service.impl;

import com.transaction.constan.UserRole;
import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.response.LoginResponse;
import com.transaction.dto.response.RegisterResponse;
import com.transaction.entity.Role;
import com.transaction.entity.UserAccount;
import com.transaction.repository.UserAccountRepository;
import com.transaction.service.AuthService;
import com.transaction.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {
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

        return RegisterResponse.builder()
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        Optional<UserAccount> currentUser = userAccountRepository.findByUsername(request.getUsername());

        if(currentUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.OK, "user exist");
        }

        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(List.of(admin, customer))
                .isNabled(true)
                .build();
        userAccountRepository.saveAndFlush(account);

        return RegisterResponse.builder()
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
