package com.aditya.restaurant.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {
    private String name;
    private Long price;
}
