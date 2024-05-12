package com.transaction.service;

import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAll(SearchCustomerRequest searchCustomerRequest);
    Customer getById(String id);
}
