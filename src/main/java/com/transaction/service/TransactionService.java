package com.transaction.service;

import com.transaction.dto.request.TransactionRequest;
import com.transaction.dto.response.TransactionResponse;
import com.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction create(TransactionRequest request);
    List<TransactionResponse> getAll();

}
