package com.transaction.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponseCustomer {
    private String name;
    private String phone;
    private String username;
    private List<String> roles;
}
