package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.constant.UserRole;
import com.aditya.restaurant.entity.Role;
import com.aditya.restaurant.repository.RoleRepository;
import com.aditya.restaurant.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role)
                .orElseGet(()-> roleRepository.saveAndFlush(
                        Role.builder().role(role).build()
                ));
    }
}
