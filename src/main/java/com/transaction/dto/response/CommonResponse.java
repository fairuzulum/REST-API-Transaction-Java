package com.transaction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;
}