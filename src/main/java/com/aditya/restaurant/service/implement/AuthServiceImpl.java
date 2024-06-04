package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.constant.UserRole;
import com.aditya.restaurant.dto.request.AuthRequest;
import com.aditya.restaurant.dto.response.LoginResponse;
import com.aditya.restaurant.dto.response.RegisterResponse;
import com.aditya.restaurant.entity.Role;
import com.aditya.restaurant.entity.UserAccount;
import com.aditya.restaurant.repository.UserAccountRepository;
import com.aditya.restaurant.service.AuthService;
import com.aditya.restaurant.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;

    @Value("${restaurant.superadmin.username}")
    private String superAdminUsername;

    @Value("${restaurant.superadmin.password}")
    private String superAdminPassword;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin () {
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(superAdminUsername);
        if (userAccount.isPresent()) {
            return; //kalau sudah ada direturn aja
        }

        Role superAdmin = roleService.getOrSave(UserRole.ROLE_SUPER_ADMIN);
        Role admin = roleService.getOrSave(UserRole.ROLE_ADMIN);
        Role customer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        UserAccount account = UserAccount.builder()
                .username(superAdminUsername)
                .password(superAdminPassword)
                .role(List.of(superAdmin, admin, customer))
                .isEnable(true)
                .build();
        userAccountRepository.saveAndFlush(account);
    }



    @Override
    public RegisterResponse register(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
