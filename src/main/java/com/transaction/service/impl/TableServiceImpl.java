package com.transaction.service.impl;


import com.transaction.dto.request.TableRequest;
import com.transaction.entity.Menu;
import com.transaction.entity.Table;
import com.transaction.repository.TableRespository;
import com.transaction.service.TableService;
import com.transaction.specification.MenuSpecification;
import com.transaction.specification.TableSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TableServiceImpl implements TableService {

    private final TableRespository tableRespository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Table create(Table table) {
        return tableRespository.saveAndFlush(table);
    }

    @Transactional(readOnly = true)
    @Override
    public Table getById(Long id) {
        return findByIdOrThrowNotFound(String.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Table update(Table request) {
        return tableRespository.saveAndFlush(request);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Table> getAll(TableRequest request) {
        if (request.getPage() <= 0){
            request.setPage(1);
        }

        String validSortBy;
        if("tableName".equalsIgnoreCase(request.getSortBy())){
            validSortBy = request.getSortBy();
        }else {
            validSortBy = "tableName";
        }

        Sort sortBy = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sortBy);

        Specification<Table> specification = TableSpecification.getSpecification(request);
        return tableRespository.findAll(specification, pageable);
    }

    private Table findByIdOrThrowNotFound(String id) {
        return tableRespository.findById(id).orElseThrow(() -> new  RuntimeException("Table Not Found"));
    }



}
