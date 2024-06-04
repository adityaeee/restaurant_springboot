package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.request.AuthRequest;
import com.aditya.restaurant.dto.response.LoginResponse;
import com.aditya.restaurant.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register (AuthRequest request);
    LoginResponse login(AuthRequest request);
//    RegisterResponse registerAdmin (AuthRequest request);
}
