package com.transaction.service.impl;


import com.transaction.entity.Menu;
import com.transaction.entity.Table;
import com.transaction.repository.TableRespository;
import com.transaction.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TableServiceImpl implements TableService {

    private final TableRespository tableRespository;

    @Override
    public Table create(Table table) {
        return tableRespository.saveAndFlush(table);
    }

    @Override
    public Table getById(Long id) {
        Optional<Table> optionalTable = tableRespository.findById(String.valueOf(id));
        if(optionalTable.isEmpty()){
            throw new RuntimeException("Menu not found");
        } else {
            return optionalTable.get();
        }
    }

}
