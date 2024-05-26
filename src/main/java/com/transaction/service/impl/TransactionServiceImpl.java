package com.transaction.service.impl;

import com.transaction.constan.TransType;
import com.transaction.dto.request.TransactionRequest;
import com.transaction.entity.*;
import com.transaction.repository.TransactionRepository;
import com.transaction.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final CustomerService customerService;
    private final MenuService menuService;
    private final TransactionTypeService transactionTypeService;
    private final TableService tableService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Transaction create(TransactionRequest request) {

        Customer customer = customerService.getById(request.getCustomerId());
        TransactionType transactionType = transactionTypeService.getDescription(request.getTransType());
        Table table = tableService.getById(request.getTableId());

        Transaction transaction = Transaction.builder()
                .customer(customer)
                .transDate(new Date())
                .transactionType(transactionType)
                .table(table)
                .build();
        transactionRepository.saveAndFlush(transaction);

        List<TransactionDetail> transactionDetails = request.getTransactionDetails().stream()
                .map(detailRequest -> {
                    Menu menu = menuService.getById(detailRequest.getMenuId());

                    return TransactionDetail.builder()
                            .menu(menu)
                            .transaction(transaction)
                            .qty(detailRequest.getQty())
                            .build();
                }).toList();

        transactionDetailService.createBulk(transactionDetails);
        transaction.setTransactionDetails(transactionDetails);

        return transaction;
    }
}
