package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RootUriBuilderFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.MENU)
public class MenuController {
    private MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody Menu request){
        return menuService.create(request);
    }

    @GetMapping
    public List<Menu> getAllMenu() {
        return menuService.getAll();
    }

}
