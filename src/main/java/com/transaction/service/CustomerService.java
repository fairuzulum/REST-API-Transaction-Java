package com.transaction.service;

import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.dto.request.UpdateCustomerRequest;
import com.transaction.dto.request.UpdateStatusCustomerRequest;
import com.transaction.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer create(Customer customer);
    Page<Customer> getAll(SearchCustomerRequest searchCustomerRequest);
    Customer getById(String id);

    Customer update(UpdateCustomerRequest updateCustomerRequest);
    Customer updateStatus(UpdateStatusCustomerRequest updateStatusCustomerRequest);
}
