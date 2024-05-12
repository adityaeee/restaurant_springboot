package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.dto.request.BillDetailRequest;
import com.aditya.restaurant.dto.response.BillDetailResponse;
import com.aditya.restaurant.entity.BillDetail;
import com.aditya.restaurant.repository.BillDetailRepository;
import com.aditya.restaurant.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> createBulk(List<BillDetail> requests) {
        return billDetailRepository.saveAllAndFlush(requests);

    }
}
