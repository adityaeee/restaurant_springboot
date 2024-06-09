package com.aditya.restaurant.service.implement;

import com.aditya.restaurant.dto.request.SearchMenuRequest;
import com.aditya.restaurant.dto.request.ValidationMenuRequest;
import com.aditya.restaurant.dto.response.MenuResponse;
import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.repository.MenuRepository;
import com.aditya.restaurant.service.MenuService;
import com.aditya.restaurant.specification.MenuSpecification;
import com.aditya.restaurant.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Menu create(ValidationMenuRequest request) {
        validationUtil.validate(request);

        Menu newMenu = Menu.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();

        return menuRepository.saveAndFlush(newMenu);
    }

    @Override
    public Menu getById(String id) {
        return findMenuOrElseThrowException(id);
    }

    @Override
    public Page<MenuResponse> getAll(SearchMenuRequest request) {

        if(request.getPage() <= 0) {
            request.setPage(1);
        }

        String validSortBy;
        if("name".equalsIgnoreCase(request.getSortBy()) || "price".equalsIgnoreCase(request.getSortBy())) {
            validSortBy = request.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((request.getPage()-1), request.getSize(),sort);

        Specification<Menu> menuSpecification = MenuSpecification.getSpecification(request);

        Page<Menu> menuPage = menuRepository.findAll(menuSpecification, pageable);

        return menuPage.map(menu -> {
            return MenuResponse.builder()
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .build();
        });
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
        menu.setDeleted(true);
        menuRepository.saveAndFlush(menu);
    }

    private Menu findMenuOrElseThrowException(String id) {
        Menu menu = menuRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu not found!"));
        if (menu.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu not found!");
        }
        return menu;
    }
}
