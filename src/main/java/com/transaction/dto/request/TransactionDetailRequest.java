package com.transaction.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetailRequest {
    private String menuId;
    private Integer qty;
}
