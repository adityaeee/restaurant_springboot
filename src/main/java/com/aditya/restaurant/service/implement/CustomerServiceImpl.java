package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.repository.CustomerRepository;
import com.aditya.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        return findCustomerOrThrowNotFound(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer request) {
        Customer customer = findCustomerOrThrowNotFound(request.getId());
        if (request.getName() == null) {
            request.setName(customer.getName());
        }
        if (request.getMember() == null) {
            request.setMember(customer.getMember());
        }
        if (request.getPhone() == null) {
            request.setPhone(customer.getPhone());
        }

        return customerRepository.saveAndFlush(request);
    }

    @Override
    public void delete(String id) {
        Customer customer = findCustomerOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    public Customer findCustomerOrThrowNotFound (String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
