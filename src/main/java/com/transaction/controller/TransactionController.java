package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.BillRequest;
import com.transaction.dto.response.BillResponse;
import com.transaction.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {
    private final BillService billService;

    @PostMapping
    public BillResponse createNewTransaction(
            @RequestBody BillRequest request
    ){
        return billService.create(request);
    }
    @GetMapping
    private List<BillResponse> getAllTransaction(){
        return billService.getAll();
    }
}
