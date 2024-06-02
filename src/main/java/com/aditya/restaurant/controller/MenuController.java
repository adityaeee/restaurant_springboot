package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.request.SearchMenuRequest;
import com.aditya.restaurant.dto.response.CommonResponse;
import com.aditya.restaurant.dto.response.MenuResponse;
import com.aditya.restaurant.dto.response.PagingResponse;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RootUriBuilderFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<List<MenuResponse>>> getAllMenu(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "price", required = false) Long price,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .direction(direction)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .name(name)
                .price(price)
                .build();

        Page<MenuResponse> menus = menuService.getAll(searchMenuRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(menus.getTotalPages())
                .totalElements(menus.getTotalElements())
                .page(menus.getPageable().getPageNumber())
                .size(menus.getPageable().getPageSize())
                .hasNext(menus.hasNext())
                .hasPrevious(menus.hasPrevious())
                .build();

        CommonResponse<List<MenuResponse>> response = CommonResponse.<List<MenuResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(menus.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
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