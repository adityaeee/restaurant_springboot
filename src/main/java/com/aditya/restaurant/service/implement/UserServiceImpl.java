package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.entity.UserAccount;
import com.aditya.restaurant.repository.UserAccountRepository;
import com.aditya.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getUserById(String id) {
        return userAccountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getByContent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userAccountRepository.findByUsername(authentication.getPrincipal().toString()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found  from context"));
    }
}
