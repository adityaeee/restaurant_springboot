package com.aditya.restaurant.repository;

import com.aditya.restaurant.constant.UserRole;
import com.aditya.restaurant.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
