package com.aditya.restaurant.dto.response;

import com.aditya.restaurant.constant.Member;
import com.aditya.restaurant.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String phone;
    private String address;
    private Date birthDate;
}
