package com.transaction.service;

import com.transaction.dto.response.JwtClaimns;
import com.transaction.entity.UserAccount;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    String generateToken(UserAccount userAccount);
    Boolean verifyJwtToken(String token);
    JwtClaimns getClaimsByToken(String token);
}
