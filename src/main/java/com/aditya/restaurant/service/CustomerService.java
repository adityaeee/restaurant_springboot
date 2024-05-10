package com.aditya.restaurant.service;

import com.aditya.restaurant.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    Customer update (Customer customer);
    void delete(String id);
}
