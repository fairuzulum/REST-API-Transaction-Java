package com.transaction.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailRequest {
    private String menuId;
    private String tableId;
    private String transTypeId;
    private Integer qty;
}
