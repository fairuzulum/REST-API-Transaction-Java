package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.request.AuthRequestCustomer;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.LoginResponse;
import com.transaction.dto.response.RegisterResponse;
import com.transaction.dto.response.RegisterResponseCustomer;
import com.transaction.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register/admin")
    public ResponseEntity<CommonResponse<?>> registerAdmin(@RequestBody AuthRequest request){
        RegisterResponse register = authService.registerAdmin(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Register Success")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "register")
    public ResponseEntity<CommonResponse<?>> registerCustomer(@RequestBody AuthRequestCustomer request){
        RegisterResponseCustomer register = authService.register(request);
        CommonResponse<RegisterResponseCustomer> response = CommonResponse.<RegisterResponseCustomer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Register Customer Succss")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<?>> login(@RequestBody AuthRequest request){
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("login Success")
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
