package com.transaction.dto.response;

import com.transaction.constan.TransType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String customerName;
    private Date transDate;
    private String tableName;
    private TransType typeTrans;
    private List<TransactionDetailResponse> transactionDetails;
}
