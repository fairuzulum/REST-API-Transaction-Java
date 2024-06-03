package com.transaction.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusCustomerRequest {
    private String id;
    private String isMember;
}
