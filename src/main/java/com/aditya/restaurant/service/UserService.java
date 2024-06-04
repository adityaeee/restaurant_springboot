package com.aditya.restaurant.service;

import com.aditya.restaurant.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserAccount getUserById (String id);

    UserAccount getByContent();

}
