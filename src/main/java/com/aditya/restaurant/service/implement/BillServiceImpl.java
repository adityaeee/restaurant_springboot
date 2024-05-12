package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillDetailResponse;
import com.aditya.restaurant.dto.response.BillResponse;
import com.aditya.restaurant.entity.*;
import com.aditya.restaurant.repository.BillRepository;
import com.aditya.restaurant.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillDetailService billDetailService;
    private final CustomerService customerService;
    private final TransTypeService transTypeService;
    private final TableService tableService;
    private final MenuService menuSerive;

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
                .transType(transType)
                .transDate(new Date())
                .build();
        billRepository.saveAndFlush(bill);

//        3. save ke bill detail
        List<BillDetail> billDetails = request.getBillDetails().stream()
                .map(detailRequest -> {
                    Menu menu = menuSerive.getById(detailRequest.getMenuId());

                    return BillDetail.builder()
                            .bill(bill)
                            .menu(menu)
                            .quantity(detailRequest.getQty())
                            .build();
                }).toList();

        billDetailService.createBulk(billDetails);
        bill.setBillDetails(billDetails);

//        4. return datanya berdasarkan bill response
        List<BillDetailResponse> billDetailResponses = billDetails.stream()
                .map(billDetail -> {

                    Menu menu = menuSerive.getById(billDetail.getId());

                    return BillDetailResponse.builder()
                            .id(billDetail.getId())
                            .menu(menu.getName())
                            .menuPrice(menu.getPrice())
                            .qty(billDetail.getQuantity())
                            .totPrice((Long) menu.getPrice() * billDetail.getQuantity())
                            .build();
                }).toList();

        Long totPriceBill = billDetailResponses.stream()
                .mapToLong(BillDetailResponse::getTotPrice
                ).sum();

        return BillResponse.builder()
                .id(bill.getId())
                .customerName(customer.getName())
                .table(table.getName())
                .transType(String.valueOf(transType.getDescription()))
                .totPrice(totPriceBill)
                .paymentAmount(request.getPaymentAmount())
                .change(request.getPaymentAmount() - totPriceBill)
                .transDate(bill.getTransDate())
                .billDetails(billDetailResponses)
                .build();
    }
}
