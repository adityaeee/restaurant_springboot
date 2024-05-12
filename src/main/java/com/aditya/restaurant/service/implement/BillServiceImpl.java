package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;
import com.aditya.restaurant.entity.Bill;
import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.repository.BillRepository;
import com.aditya.restaurant.service.BillService;
import com.aditya.restaurant.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final CustomerService customerService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public BillResponse create(BillRequest request) {
//        1. cek customer, transType dan table
        Customer customer = customerService.getById(request.getCustomerId());

//        2. save ke bill
        Bill bill = Bill.builder()
                .customer(customer)
                .transDate(new Date())
                .build();

        return null;
    }
}
