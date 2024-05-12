package com.aditya.restaurant.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailRequest {
    private String menuId;
    private Integer qty;
}
