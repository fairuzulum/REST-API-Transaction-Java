package com.transaction.service;

import com.transaction.entity.Table;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TableService {
    Table create(Table table);
    Table getById(Long id);
}
