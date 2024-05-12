package com.aditya.restaurant.repository;

import com.aditya.restaurant.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransTypeRepository extends JpaRepository<TransType, String> {
}
