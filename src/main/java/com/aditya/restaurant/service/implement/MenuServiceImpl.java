package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.repository.MenuRepository;
import com.aditya.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public Menu create(Menu request) {
        return menuRepository.saveAndFlush(request);
    }

    @Override
    public Menu getById(String id) {
        return findMenuOrElseThrowException(id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu update(Menu request) {
        Menu menu = findMenuOrElseThrowException(request.getId());

        if (request.getName() == null) {
            request.setName(menu.getName());
        }

        if (request.getPrice() == null) {
            request.setPrice(menu.getPrice());
        }

        return menuRepository.saveAndFlush(request);
    }

    @Override
    public void delete(String id) {
        Menu menu = findMenuOrElseThrowException(id);
        menuRepository.delete(menu);
    }

    private Menu findMenuOrElseThrowException(String id) {
        return menuRepository.findById(id).orElseThrow(()-> new RuntimeException("Menu not found!"));
    }
}
