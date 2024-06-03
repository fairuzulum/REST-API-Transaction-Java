package com.transaction.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailResponse {
    private String menuName;
    private Long menuPrice;
    private Integer quantity;
    private Long total;
}
