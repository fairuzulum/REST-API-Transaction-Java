package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.entity.TransactionType;
import com.transaction.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TRANSACTION_TYPE_API)
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;
    @PostMapping
    public TransactionType createTransactionType(@RequestBody TransactionType transactionType) {
        return transactionTypeService.create(transactionType);
    }
}
