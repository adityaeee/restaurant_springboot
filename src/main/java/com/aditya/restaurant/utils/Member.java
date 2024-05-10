package com.aditya.restaurant.utils;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Member {
    MEMBER("restaurant members"),
    NOT_MEMBER("not a restaurant members");

    private final String value;
}
