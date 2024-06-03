package com.transaction.service.impl;


import com.transaction.constan.UserRole;
import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.dto.request.UpdateCustomerRequest;
import com.transaction.dto.request.UpdateStatusCustomerRequest;
import com.transaction.entity.Customer;
import com.transaction.entity.Role;
import com.transaction.entity.UserAccount;
import com.transaction.repository.CustomerRepository;
import com.transaction.service.CustomerService;
import com.transaction.service.UserService;
import com.transaction.specification.CustomerSpecification;
import com.transaction.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(Customer customer) {
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getUsername().equals("admin")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
        }
        validationUtil.validate(customer);
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> getAll(SearchCustomerRequest request) {
        if (request.getPage() <= 0){
            request.setPage(1);
        }
        String validSortBy;
        if("name".equalsIgnoreCase(request.getSortBy()) || "phone".equalsIgnoreCase(request.getSortBy())){
            validSortBy = request.getSortBy();
        }else {
            validSortBy = "name";
        }
        Sort sortBy = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sortBy);
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll(specification, pageable);
    }


    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer update(UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = getById(updateCustomerRequest.getId());
        UserAccount userAccount = userService.getByContext();

        if (!userAccount.getUsername().equals("admin")){
            if (!userAccount.getId().equals(customer.getUserAccount().getId())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
            }
        }

        Customer update = Customer.builder()
                .id(updateCustomerRequest.getId())
                .name(updateCustomerRequest.getName())
                .phone(updateCustomerRequest.getPhone())
                .isMember(customer.getIsMember())
                .userAccount(customer.getUserAccount())
                .build();
       return customerRepository.saveAndFlush(update);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer updateStatus(UpdateStatusCustomerRequest request) {

        Customer customer = getById(request.getId());
        UserAccount userAccount = userService.getByContext();

        if (!userAccount.getUsername().equals("admin")){
            if (!userAccount.getId().equals(customer.getUserAccount().getId())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
            }
        }
        Customer update = Customer.builder()
                .id(request.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .isMember(Boolean.valueOf(request.getIsMember()))
                .userAccount(userAccount)
                .build();
        return customerRepository.saveAndFlush(update);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new  RuntimeException("Customer not found"));
    }

}
