package com.transaction.controller;


import com.transaction.constan.APIUrl;
import com.transaction.constan.TransType;
import com.transaction.entity.TransactionType;
import com.transaction.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TRANSACTION_TYPE_API)
public class TransTypeController {

    private final TransactionTypeService transactionTypeService;

    @PostMapping
    public ResponseEntity<TransactionType> create(@RequestBody TransactionType request) {
        TransactionType add = transactionTypeService.create(request.getDescription());
        return ResponseEntity.ok().body(add);
    }
}
