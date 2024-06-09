package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.response.CommonResponse;
import com.aditya.restaurant.dto.response.CustomerResponse;
import com.aditya.restaurant.entity.Customer;
import com.aditya.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> createCustomer (@RequestBody Customer request){
        Customer customer = customerService.create(request);
        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .build();

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(customerResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    };

    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomerById (@PathVariable String id) {
        Customer customer = customerService.getById(id);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .build();

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customerResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    };

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer () {
        List<Customer> customers = customerService.getAll();

        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> {
                    return CustomerResponse.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .phone(customer.getPhone())
                            .address(customer.getAddress())
                            .birthDate(customer.getBirthDate())
                            .build();
                }).toList();

        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customerResponses)
                .build();

        return ResponseEntity.ok(response);
    }


    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer (
            @RequestBody Customer request
    ) {
        Customer customer = customerService.update(request);

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .build();

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(customerResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<String>> deleteCustomer (@PathVariable String id) {
        customerService.delete(id);
        String res = "OK, Success delete customer";

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
