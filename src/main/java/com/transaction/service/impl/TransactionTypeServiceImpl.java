package com.transaction.service.impl;

import com.transaction.constan.TransType;
import com.transaction.entity.TransactionType;
import com.transaction.repository.TransactionTypeRepository;
import com.transaction.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    @Override
    public TransactionType create(TransType transType) {
        TransactionType type = TransactionType.builder()
                .description(transType)
                .build();
        return transactionTypeRepository.saveAndFlush(type);
    }

    @Override
    public TransactionType getDescription(TransType transType) {
        return transactionTypeRepository.findByDescription(transType);
    }
}
