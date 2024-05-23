package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RootUriBuilderFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU)
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody Menu request){
        return menuService.create(request);
    }

    @GetMapping
    public List<Menu> getAllMenu() {
        return menuService.getAll();
    }

    @GetMapping(path = APIUrl.PATH_ID)
    public Menu getById(@PathVariable String id) {
        return menuService.getById(id);
    }

    @PutMapping
    public Menu updateMenu(@RequestBody Menu request) {
        return menuService.update(request);
    }

    @DeleteMapping(path = APIUrl.PATH_ID)
    public String deleteMenu(@PathVariable String id) {
        menuService.delete(id);
        return "The menu has been successfully deleted";
    }


}