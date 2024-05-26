package com.transaction.service;

import com.transaction.dto.request.TransactionRequest;
import com.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Transaction create(TransactionRequest request);
}
