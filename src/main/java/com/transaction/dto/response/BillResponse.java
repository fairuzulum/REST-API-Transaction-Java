package com.transaction.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class BillResponse {
    private String id;
    private String customerId;
    private Date transDate;
    private List<BillDetailResponse> billDetails;
}
