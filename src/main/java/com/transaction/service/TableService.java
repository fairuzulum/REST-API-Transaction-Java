package com.transaction.service;

import com.transaction.dto.request.TableRequest;
import com.transaction.entity.Table;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TableService {
    Table create(Table table);
    Table getById(Long id);
    Table update(Table request);
    Page<Table> getAll(TableRequest request);
}
