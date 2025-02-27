package com.aditya.restaurant.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransTypeDes {
    CASH("cash payment"),
    NON_CASH("non-cash payment");

    private final String value;

}
