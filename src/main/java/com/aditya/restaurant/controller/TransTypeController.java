package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.response.CommonResponse;
import com.aditya.restaurant.entity.Menu;
import com.aditya.restaurant.entity.Tables;
import com.aditya.restaurant.entity.TransType;
import com.aditya.restaurant.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANS_TYPE)
public class TransTypeController {

    private final TransTypeService transTypeService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransType>> createTransType(@RequestBody TransType request){
        TransType transType = transTypeService.create(request);
        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(transType)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransType>>> getAllTransType() {
        List<TransType> transTypes = transTypeService.getAll();

        CommonResponse<List<TransType>> response = CommonResponse.<List<TransType>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(transTypes)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<TransType>> getByIdTransType (@PathVariable String id) {
        TransType transType = transTypeService.getById(id);

        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(transType)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TransType>> updateTransType (@RequestBody TransType request) {
        TransType transType = transTypeService.update(request);

        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(transType)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<String>> deleteTransType(@PathVariable String id) {
        transTypeService.delete(id);

        String res = "OK, Success delete trans type";

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    };
}
