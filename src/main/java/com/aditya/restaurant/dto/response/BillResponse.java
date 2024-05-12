package com.aditya.restaurant.dto.response;

import com.aditya.restaurant.entity.BillDetail;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillResponse {
    private String id;
    private String customerId;
    private Date transDate;
    private List<BillDetailResponse> billDetails;
}
