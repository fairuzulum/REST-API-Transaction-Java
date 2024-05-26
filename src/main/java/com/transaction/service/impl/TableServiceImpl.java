package com.transaction.service.impl;


import com.transaction.entity.Table;
import com.transaction.repository.TableRespository;
import com.transaction.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableServiceImpl implements TableService {

    private final TableRespository tableRespository;

    @Override
    public Table create(Table table) {
        return tableRespository.saveAndFlush(table);
    }

}
