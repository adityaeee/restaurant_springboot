package com.aditya.restaurant.entity;

import com.aditya.restaurant.constant.ConstantTable;
import com.aditya.restaurant.constant.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
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

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")   // yyyy-mm-dd : format date json (from Util)
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
