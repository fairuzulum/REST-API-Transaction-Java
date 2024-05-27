package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.PagingResponse;
import com.transaction.entity.Customer;
import com.transaction.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,

            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,

            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone
    ){
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .phone(phone)
                .build();
        Page<Customer> allCustomer = customerService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allCustomer.getTotalPages())
                .totalElements(allCustomer.getTotalElements())
                .page(allCustomer.getPageable().getPageNumber() + 1)
                .size(allCustomer.getPageable().getPageSize())
                .hasNext(allCustomer.hasNext())
                .hasPrevious(allCustomer.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Data Found")
                .data(allCustomer.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }

}
