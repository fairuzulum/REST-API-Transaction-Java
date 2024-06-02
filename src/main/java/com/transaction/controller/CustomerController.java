package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.SearchCustomerRequest;
import com.transaction.dto.request.UpdateCustomerRequest;
import com.transaction.dto.request.UpdateStatusCustomerRequest;
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
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer getCustomer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("SUCCESS GET DATA")
                .data(getCustomer)
                .build();
        return ResponseEntity.ok().body(response);
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

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> update(@RequestBody UpdateCustomerRequest request){
        Customer update = customerService.update(request);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("SUCCES UPDATE DATA CUSTOMER")
                .data(update)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> updateStatus(@RequestBody UpdateStatusCustomerRequest request){
        Customer update = customerService.updateStatus(request);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("SUCCES UPDATE STATUS CUSTOMER")
                .data(update)
                .build();
        return ResponseEntity.ok().body(response);
    }




}
