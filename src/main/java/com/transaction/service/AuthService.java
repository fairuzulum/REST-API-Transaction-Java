package com.transaction.service;

import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.response.LoginResponse;
import com.transaction.dto.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
