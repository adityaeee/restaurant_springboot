package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.response.JwtClaims;
import com.aditya.restaurant.entity.UserAccount;

public interface JwtService {
    String generateToken (UserAccount userAccount);

    boolean verifyJwtToken(String token);

    JwtClaims getClaimsByToken (String token);
}
