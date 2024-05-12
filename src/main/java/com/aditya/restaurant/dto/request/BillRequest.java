package com.aditya.restaurant.dto.request;

import lombok.Builder;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@Builder
public class BillRequest {
    private String customerId;
    private String tableId;
    private String transTypeId;
    private Long paymentAmount;
    private List<BillDetailRequest> billDetails;
}
