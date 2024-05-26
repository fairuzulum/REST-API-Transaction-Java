package com.transaction.service;

import com.transaction.entity.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionDetailService {
    List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails);
}
