package com.transaction.dto.request;


import lombok.*;

@Data
@Builder
public class SearchMenuRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String name;
}
