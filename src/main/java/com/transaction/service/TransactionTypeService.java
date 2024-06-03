package com.transaction.service;

import com.transaction.constan.TransType;
import com.transaction.entity.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TransactionTypeService {
    TransactionType create(TransType transType);
    TransactionType getDescription(TransType transType);
}
