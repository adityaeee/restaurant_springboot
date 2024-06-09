package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;
import com.aditya.restaurant.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BILL)
public class BillController {

    private final BillService billService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public BillResponse createBill(@RequestBody BillRequest request) {
        return billService.create(request);
    };

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public List<BillResponse> getAllBill() {
        return billService.getAllBill();
    }
}
