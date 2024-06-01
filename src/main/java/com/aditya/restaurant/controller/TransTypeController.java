package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.entity.TransType;
import com.aditya.restaurant.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANS_TYPE)
public class TransTypeController {

    private final TransTypeService transTypeService;

    @PostMapping
    public TransType createTransType(@RequestBody TransType request){
        return transTypeService.create(request);
    }

    @GetMapping
    public List<TransType> getAllMenu() {
        return transTypeService.getAll();
    }
}
