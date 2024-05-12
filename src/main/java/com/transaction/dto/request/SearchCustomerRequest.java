package com.transaction.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String phone;
    private Boolean is_member;

}
