package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.Tables;
import com.aditya.restaurant.repository.TableRepository;
import com.aditya.restaurant.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    @Override
    public Tables create(Tables request) {
        return tableRepository.saveAndFlush(request);
    }

    @Override
    public Tables getById(String id) {
        return findTableOrThrowNotFound(id);
    }

    @Override
    public List<Tables> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public Tables update(Tables request) {
        Tables table = findTableOrThrowNotFound(request.getId());
        return tableRepository.saveAndFlush(table);
    }

    @Override
    public void delete(String id) {
        Tables table = findTableOrThrowNotFound(id);
        tableRepository.delete(table);
    }

    public Tables findTableOrThrowNotFound (String id) {
        return tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
    }

}
