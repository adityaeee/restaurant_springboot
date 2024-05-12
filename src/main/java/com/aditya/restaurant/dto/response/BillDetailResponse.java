package com.aditya.restaurant.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailResponse {
    private String id;
    private String productId;
    private Long productPrice;
    private Integer qty;
}
