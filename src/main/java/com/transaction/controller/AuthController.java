package com.transaction.controller;

import com.transaction.constan.APIUrl;
import com.transaction.dto.request.AuthRequest;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.RegisterResponse;
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

}
