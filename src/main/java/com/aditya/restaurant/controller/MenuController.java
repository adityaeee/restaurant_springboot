package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.request.SearchMenuRequest;
import com.aditya.restaurant.dto.request.ValidationMenuRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU)
public class MenuController {
    private final MenuService menuService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse<MenuResponse>> createMenu(@RequestBody ValidationMenuRequest request){
        Menu newMenu = menuService.create(request);

        MenuResponse menuResponse = MenuResponse.builder()
                .name(newMenu.getName())
                .price(newMenu.getPrice())
                .build();

        CommonResponse<MenuResponse>response =CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(menuResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN', 'CUSTOMER')")
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<MenuResponse>> getById(@PathVariable String id) {
        Menu menu = menuService.getById(id);
        MenuResponse menuResponse = MenuResponse.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .build();

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(menuResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<CommonResponse<MenuResponse>> updateMenu(@RequestBody Menu request) {
        Menu menu = menuService.update(request);

        MenuResponse menuResponse = MenuResponse.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .build();

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(menuResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<String>> deleteMenu(@PathVariable String id) {
        menuService.delete(id);
        String res =  "The menu has been successfully deleted";

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}