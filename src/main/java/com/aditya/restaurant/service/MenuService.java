package com.aditya.restaurant.service;

import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.entity.Tables;

import java.util.List;

public interface MenuService {
    Menu create(Menu request);
    Menu getById(String id);
    List<Menu> getAll();
    Menu update(Menu request);
    void delete(String id);
}
