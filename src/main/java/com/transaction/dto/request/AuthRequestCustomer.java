package com.transaction.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestCustomer {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password required")
    private String password;
}
