package com.transaction.dto.response;


import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtClaimns {
    private String userAccountId;
    private List<String> roles;
}
