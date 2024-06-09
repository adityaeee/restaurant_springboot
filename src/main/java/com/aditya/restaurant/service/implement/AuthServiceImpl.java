package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.constant.Member;
import com.aditya.restaurant.constant.UserRole;
import com.aditya.restaurant.dto.request.AuthRequest;
import com.aditya.restaurant.dto.response.LoginResponse;
import com.aditya.restaurant.dto.response.RegisterResponse;
import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.Role;
import com.aditya.restaurant.entity.UserAccount;
import com.aditya.restaurant.repository.UserAccountRepository;
import com.aditya.restaurant.service.AuthService;
import com.aditya.restaurant.service.CustomerService;
import com.aditya.restaurant.service.JwtService;
import com.aditya.restaurant.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

//    create bean
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {
        Role roleCustomer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);

        String hasPassword = passwordEncoder.encode(request.getPassword());

        UserAccount userAccount = UserAccount.builder()
                .username(request.getUsername())
                .password(hasPassword)
                .isEnable(true)
                .role(List.of(roleCustomer))
                .build();
        userAccountRepository.saveAndFlush(userAccount);

        Customer customer = Customer.builder()
                .member(Member.MEMBER)
                .userAccount(userAccount)
                .build();
        customerService.create(customer);

        return RegisterResponse.builder()
                .username(userAccount.getUsername())
                .roles(userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication autenticated = authenticationManager.authenticate(authentication);

        UserAccount userAccount = (UserAccount) autenticated.getPrincipal();

        String token = jwtService.generateToken(userAccount);

        return LoginResponse.builder()
                .username(userAccount.getUsername())
                .token(token)
                .roles(userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
