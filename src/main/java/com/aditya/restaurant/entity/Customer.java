package com.aditya.restaurant.entity;

import com.aditya.restaurant.constant.ConstantTable;
import com.aditya.restaurant.constant.Member;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Member member;
}
