package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.entity.Customer;
import com.transaction.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {

    final CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

}
