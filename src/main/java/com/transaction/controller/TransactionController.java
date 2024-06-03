package com.transaction.controller;


import com.transaction.constan.APIUrl;
import com.transaction.dto.request.TransactionRequest;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.TransactionDetailResponse;
import com.transaction.dto.response.TransactionResponse;
import com.transaction.entity.Transaction;
import com.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.create(request);

        List<TransactionDetailResponse> trxDetailResponse = transaction.getTransactionDetails().stream()
                .map(detail -> TransactionDetailResponse.builder()
                .menuName(detail.getMenu().getName())
                .menuPrice(detail.getMenu().getPrice())
                .quantity(detail.getQty())
                .total(detail.getMenu().getPrice() * detail.getQty())
                .build()).toList();

        TransactionResponse trx = TransactionResponse.builder()
                .customerName(transaction.getCustomer().getName())
                .transDate(transaction.getTransDate())
                .tableName(transaction.getTable().getTableName())
                .typeTrans(transaction.getTransactionType().getDescription())
                .transactionDetails(trxDetailResponse)
                .build();

        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Transaction Success")
                .data(trx)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<TransactionResponse> getAllTransaction(){
        return transactionService.getAll();
    }

}
