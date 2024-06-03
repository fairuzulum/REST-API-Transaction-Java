package com.transaction.controller;


import com.transaction.constan.APIUrl;
import com.transaction.dto.request.TableRequest;
import com.transaction.dto.response.CommonResponse;
import com.transaction.dto.response.PagingResponse;
import com.transaction.entity.Table;
import com.transaction.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TABLE_API)
public class Tablecontroller {

    private final TableService tableService;

    @PostMapping
    public ResponseEntity<CommonResponse<Table>> createTable(@RequestBody Table table) {
        Table newTable = tableService.create(table);
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(newTable)
                .message("TABLE SUCCESS CREATED")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Table>>> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,

            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,

            @RequestParam(name = "name", required = false) String name
    ){
        TableRequest request = TableRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Table> allTable = tableService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allTable.getTotalPages())
                .totalElements(allTable.getTotalElements())
                .page(allTable.getPageable().getPageNumber() + 1)
                .size(allTable.getPageable().getPageSize())
                .hasNext(allTable.hasNext())
                .hasPrevious(allTable.hasPrevious())
                .build();

        CommonResponse<List<Table>> response = CommonResponse.<List<Table>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Data Found")
                .data(allTable.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Table>> getById(@PathVariable String id){
        Table byId = tableService.getById(Long.valueOf(id));
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.OK.value())
                .message("DATA FOUND")
                .data(byId)
                .build();
        return ResponseEntity.ok().body(response);
    }



}
