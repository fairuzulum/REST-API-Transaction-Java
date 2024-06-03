package com.transaction.service.impl;

import com.transaction.constan.TransType;
import com.transaction.entity.TransactionType;
import com.transaction.entity.UserAccount;
import com.transaction.repository.TransactionTypeRepository;
import com.transaction.service.TransactionTypeService;
import com.transaction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionType create(TransType transType) {
        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getUsername().equals("admin")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
        }
        TransactionType type = TransactionType.builder()
                .description(transType)
                .build();
        return transactionTypeRepository.saveAndFlush(type);
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionType getDescription(TransType transType) {
        return transactionTypeRepository.findByDescription(transType);
    }
}
