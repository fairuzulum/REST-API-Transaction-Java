package com.transaction.service.impl;


import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.dto.request.UpdateCustomerRequest;
import com.transaction.dto.request.UpdateStatusCustomerRequest;
import com.transaction.dto.response.CustomerResponse;
import com.transaction.entity.Customer;
import com.transaction.repository.CustomerRepository;
import com.transaction.service.CustomerService;
import com.transaction.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Customer update(UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = getById(updateCustomerRequest.getId());

        Customer update = Customer.builder()
                .id(updateCustomerRequest.getId())
                .name(updateCustomerRequest.getName())
                .phone(updateCustomerRequest.getPhone())
                .isMember(customer.getIsMember())
                .build();
       return customerRepository.saveAndFlush(update);
    }

    @Override
    public Customer updateStatus(UpdateStatusCustomerRequest request) {

        Customer customer = getById(request.getId());

        Customer update = Customer.builder()
                .id(request.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .isMember(Boolean.valueOf(request.getIsMember()))
                .build();
        return customerRepository.saveAndFlush(update);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new  RuntimeException("Customer not found"));
    }
}
