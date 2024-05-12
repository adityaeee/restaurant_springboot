package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.TransType;
import com.aditya.restaurant.repository.TransTypeRepository;
import com.aditya.restaurant.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransTypeServiceImpl implements TransTypeService {

    private final TransTypeRepository transTypeRepository;

    @Override
    public TransType create(TransType request) {
        return transTypeRepository.saveAndFlush(request);
    }

    @Override
    public TransType getById(String id) {
        return findTransTypeOrThrowNotFound(id);
    }

    @Override
    public List<TransType> getAll() {
        return transTypeRepository.findAll();
    }

    @Override
    public TransType update(TransType request) {
        TransType transType = findTransTypeOrThrowNotFound(request.getId());
        return transTypeRepository.saveAndFlush(request);
    }

    @Override
    public void delete(String id) {
        TransType transType = findTransTypeOrThrowNotFound(id);
        transTypeRepository.delete(transType);
    }

    public TransType findTransTypeOrThrowNotFound (String id){
        return transTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction type not found"));
    }
}
