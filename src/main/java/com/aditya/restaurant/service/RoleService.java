package com.aditya.restaurant.service;

import com.aditya.restaurant.constant.UserRole;
import com.aditya.restaurant.entity.Role;

public interface RoleService {
    Role getOrSave (UserRole role);
}
