package com.transaction.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String name;
}
