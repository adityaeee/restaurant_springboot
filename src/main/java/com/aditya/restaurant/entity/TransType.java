package com.aditya.restaurant.entity;

import com.aditya.restaurant.constant.ConstantTable;
import com.aditya.restaurant.constant.TransTypeDes;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.TRANS_TYPE)
public class TransType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private TransTypeDes description;
}


