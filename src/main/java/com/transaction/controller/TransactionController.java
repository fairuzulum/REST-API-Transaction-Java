package com.transaction.controller;


import com.transaction.constan.APIUrl;
import com.transaction.dto.request.TransactionRequest;
import com.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity createTransaction(@RequestBody TransactionRequest request) {
        transactionService.create(request);
        return ResponseEntity.ok().build();
    }

}
