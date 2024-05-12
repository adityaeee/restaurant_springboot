package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.request.BillRequest;
import com.aditya.restaurant.dto.response.BillResponse;

public interface BillService {
    BillResponse create(BillRequest request);
}
