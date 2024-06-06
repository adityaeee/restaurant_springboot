package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.request.AuthRequest;
import com.aditya.restaurant.dto.response.CommonResponse;
import com.aditya.restaurant.dto.response.RegisterResponse;
import com.aditya.restaurant.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH)
public class AuthController {
    private final AuthService authService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(path = "/register")
    public ResponseEntity<CommonResponse<?>> registerUser (@RequestBody AuthRequest request) {
        RegisterResponse register = authService.register(request);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(register)
                .build();

        return ResponseEntity.ok(response);
    }

}
