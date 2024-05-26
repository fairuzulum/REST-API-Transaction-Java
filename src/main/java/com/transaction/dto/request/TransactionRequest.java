package com.transaction.dto.request;


import com.transaction.constan.TransType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionRequest {

    private String customerId;
    private TransType transType;
    private Long tableId;
    private List<TransactionDetailRequest> transactionDetails;
}
