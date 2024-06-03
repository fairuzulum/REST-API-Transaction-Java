package com.transaction.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String phone;
    private String userAccountId;

}
