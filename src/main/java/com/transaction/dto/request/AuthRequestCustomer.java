package com.transaction.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestCustomer {
    private String name;
    private String phone;
    private String username;
    private String password;
}
