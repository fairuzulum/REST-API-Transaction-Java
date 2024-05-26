package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.entity.Customer;
import com.transaction.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {

    final CustomerService customerService;

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "isMember", required = false) Boolean isMember
    ) {
        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .name(name)
                .phone(phone)
                .is_member(isMember)
                .build();
        return customerService.getAll(searchCustomerRequest);
    }

}
