package com.transaction.service;

import com.transaction.entity.Table;
import org.springframework.stereotype.Service;

@Service
public interface TableService {
    Table create(Table table);
}
