package com.aditya.restaurant.service;

import com.aditya.restaurant.dto.request.BillDetailRequest;
import com.aditya.restaurant.dto.response.BillDetailResponse;
import com.aditya.restaurant.entity.BillDetail;

import java.util.List;

public interface BillDetailService {

    List<BillDetail> createBulk(List<BillDetail> requests);
}
