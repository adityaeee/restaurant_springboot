package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;
import com.aditya.restaurant.entity.*;
import com.aditya.restaurant.repository.BillRepository;
import com.aditya.restaurant.service.BillService;
import com.aditya.restaurant.service.CustomerService;
import com.aditya.restaurant.service.TableService;
import com.aditya.restaurant.service.TransTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final CustomerService customerService;
    private final TransTypeService transTypeService;
    private final TableService tableService;
//    private final MenuService menuSerive;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public BillResponse create(BillRequest request) {
//        1. cek customer, transType dan table
        Customer customer = customerService.getById(request.getCustomerId());
        TransType transType = transTypeService.getById(request.getTransTypeId());
        Tables table = tableService.getById(request.getTableId());

//        2. save ke bill
        Bill bill = Bill.builder()
                .customer(customer)
                .table(table)
                .transDate(new Date())
                .build();


//        3. save ke bill detail
//        List<BillDetail> billDetails = request.getBillDetails().stream()
//                .map(detailRequest -> {
//                    Menu menu =
//                }).toList();

        return null;
    }
}
