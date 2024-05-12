package com.transaction.controller;


import com.transaction.constan.APIUrl;
import com.transaction.entity.Table;
import com.transaction.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.TABLE_API)
public class Tablecontroller {

    private final TableService tableService;

    @PostMapping
    public Table createTable(@RequestBody Table table) {
        return tableService.create(table);
    }
}
