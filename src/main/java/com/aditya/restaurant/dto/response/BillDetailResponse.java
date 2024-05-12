package com.aditya.restaurant.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailResponse {
    private String id;
    private String menu;
    private Long menuPrice;
    private Integer qty;
    private Long totPrice;
}
