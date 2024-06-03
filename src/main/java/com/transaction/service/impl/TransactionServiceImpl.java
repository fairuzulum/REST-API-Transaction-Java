package com.transaction.service.impl;

import com.transaction.constan.TransType;
import com.transaction.dto.request.TransactionRequest;
import com.transaction.dto.response.TransactionDetailResponse;
import com.transaction.dto.response.TransactionResponse;
import com.transaction.entity.*;
import com.transaction.repository.TransactionRepository;
import com.transaction.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    private final UserService userService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Transaction create(TransactionRequest request) {

        Customer customer = customerService.getById(request.getCustomerId());
        TransactionType transactionType = transactionTypeService.getDescription(request.getTransType());
        Table table = tableService.getById(request.getTableId());

        UserAccount userAccount = userService.getByContext();
        if (!userAccount.getId().equals(customer.getUserAccount().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "permission denied");
        }

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

    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(trx -> {
            List<TransactionDetailResponse> trxDetailResponse = trx.getTransactionDetails().stream()
                    .map(detail -> TransactionDetailResponse.builder()
                    .menuName(detail.getMenu().getName())
                    .menuPrice(detail.getMenu().getPrice())
                    .quantity(detail.getQty())
                    .total(detail.getMenu().getPrice() * detail.getQty())
                    .build()).toList();


            return TransactionResponse.builder()
                    .customerName(trx.getCustomer().getName())
                    .transDate(trx.getTransDate())
                    .tableName(trx.getTable().getTableName())
                    .typeTrans(trx.getTransactionType().getDescription())
                    .transactionDetails(trxDetailResponse)
                    .build();
        }).toList();
    }


}
