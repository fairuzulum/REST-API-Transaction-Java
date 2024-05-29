package com.transaction.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponse {
    String customerId;
    String name;
    String phone;
}
