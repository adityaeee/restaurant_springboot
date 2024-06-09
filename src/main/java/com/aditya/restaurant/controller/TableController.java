package com.aditya.restaurant.controller;

import com.aditya.restaurant.constant.APIUrl;
import com.aditya.restaurant.constant.ResponseMessage;
import com.aditya.restaurant.dto.response.CommonResponse;
import com.aditya.restaurant.entity.Tables;
import com.aditya.restaurant.service.TableService;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.TABLE)
public class TableController {
    private TableService tableService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public ResponseEntity<CommonResponse<Tables>> createNewTable(@RequestBody Tables request) {
        Tables table = tableService.create(request);
        CommonResponse<Tables> response = CommonResponse.<Tables>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(table)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<Tables>>> getAllTable () {
        List<Tables> tables = tableService.getAll();
        CommonResponse<List<Tables>> response = CommonResponse.<List<Tables>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(tables)
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<Tables>> getById (@PathVariable String id) {
        Tables table = tableService.getById(id);

        CommonResponse<Tables> response = CommonResponse.<Tables>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(table)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<CommonResponse<Tables>> updateTabel (@RequestBody Tables request) {
        Tables table = tableService.update(request);

        CommonResponse<Tables> response = CommonResponse.<Tables>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(table)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<String>> deleteTable(@PathVariable String id) {
        tableService.delete(id);

        String res = "OK, Success delete table";

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    };

}
