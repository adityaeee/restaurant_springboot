package com.aditya.restaurant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchMenuRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String name;
    private Long price;
}
