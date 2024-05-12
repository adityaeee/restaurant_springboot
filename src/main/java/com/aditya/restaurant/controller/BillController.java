package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;
import com.aditya.restaurant.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BILL)
public class BillController {

    private final BillService billService;

    @PostMapping
    public BillResponse createBill(@RequestBody BillRequest request) {
        return billService.create(request);
    };
}
