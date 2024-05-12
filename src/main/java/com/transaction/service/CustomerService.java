package com.transaction.service;

import com.transaction.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public Customer create(Customer customer);
}
