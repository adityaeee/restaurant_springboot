package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.request.SearchMenuRequest;
import com.aditya.restaurant.dto.request.ValidationMenuRequest;
import com.aditya.restaurant.dto.response.MenuResponse;
import com.aditya.restaurant.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuService {
    Menu create(ValidationMenuRequest request);
    Menu getById(String id);
    Page<MenuResponse> getAll(SearchMenuRequest request);
    Menu update(Menu request);
    void delete(String id);
}
