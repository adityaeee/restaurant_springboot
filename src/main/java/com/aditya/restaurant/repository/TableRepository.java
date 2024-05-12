package com.aditya.restaurant.repository;

import com.aditya.restaurant.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables, String> {
}
