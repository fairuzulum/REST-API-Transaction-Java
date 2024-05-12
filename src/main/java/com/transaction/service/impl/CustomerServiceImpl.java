package com.transaction.service.impl;


import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.entity.Customer;
import com.transaction.repository.CustomerRepository;
import com.transaction.service.CustomerService;
import com.transaction.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest request) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(request);
        if(request.getName() == null && request.getPhone() == null && request.getIs_member() == null) {
            return customerRepository.findAll();
        }
        return customerRepository.findAll(customerSpecification);
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new  RuntimeException("Customer not found"));
    }
}
