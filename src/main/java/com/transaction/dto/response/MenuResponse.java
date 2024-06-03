package com.transaction.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponse {
    private String name;
    private Long price;
}
