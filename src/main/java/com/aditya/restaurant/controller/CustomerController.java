package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer createCustomer (@RequestBody Customer customer){
        return customerService.create(customer);
    };

    @GetMapping(path = APIUrl.PATH_ID)
    public Customer getCustomerById (@PathVariable String id) {
        return customerService.getById(id);
    };

    @GetMapping
    public List<Customer> getAllCustomer () {
        return customerService.getAll();
    }

    @PutMapping
    public Customer updateCustomer (
            @RequestBody Customer customer
    ) {
        return customerService.update(customer);
    }

    @DeleteMapping(path = APIUrl.PATH_ID)
    public String deleteCustomer (@PathVariable String id) {
        customerService.delete(id);
        return "OK, Success delete customer";
    }
}
