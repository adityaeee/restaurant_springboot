package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse create(BillRequest request);
    List<BillResponse> getAllBill();
}
