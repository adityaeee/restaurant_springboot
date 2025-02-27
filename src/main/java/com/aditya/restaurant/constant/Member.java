package com.aditya.restaurant.constant;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Member {
    MEMBER("restaurant members"),
    NOT_MEMBER("not member");

    private final String value;
}
