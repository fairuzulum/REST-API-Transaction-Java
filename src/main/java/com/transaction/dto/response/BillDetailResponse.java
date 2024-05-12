package com.transaction.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BillDetailResponse {
    private String id;
    private String menuId;
    private String tableId;
    private String transTypeId;
    private Long menuPrice;
    private Integer quantity;
}
