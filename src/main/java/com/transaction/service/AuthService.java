package com.transaction.service;

import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.request.AuthRequestCustomer;
import com.transaction.dto.response.LoginResponse;
import com.transaction.dto.response.RegisterResponse;
import com.transaction.dto.response.RegisterResponseCustomer;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponseCustomer register(AuthRequestCustomer request);
//    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
