package com.aditya.restaurant.service;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.TransType;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface TransTypeService {

    TransType create(TransType request);
    TransType getById(String id);
    List<TransType> getAll();
    TransType update (TransType request);
    void delete(String id);

//    Customer create(Customer customer);
//    Customer getById(String id);
//    List<Customer> getAll();
//    Customer update (Customer customer);
//    void delete(String id);
}
