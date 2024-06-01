package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.entity.Tables;
import com.aditya.restaurant.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.TABLE)
public class TableController {
    private TableService tableService;

    @GetMapping
    public List<Tables> getAllTable (){
        return tableService.getAll();
    }

    @PostMapping
    public Tables createNewTable(@RequestBody Tables request) {
        return tableService.create(request);
    };
}
