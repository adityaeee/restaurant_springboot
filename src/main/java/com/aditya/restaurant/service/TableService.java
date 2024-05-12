package com.aditya.restaurant.service;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.Tables;
import jakarta.persistence.Table;

import java.util.List;

public interface TableService {
    Tables create(Tables request);
    Tables getById(String id);
    List<Tables> getAll();
    Tables update (Tables request);
    void delete (String id);

}
