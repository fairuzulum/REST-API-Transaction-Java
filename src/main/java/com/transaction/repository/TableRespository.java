package com.transaction.repository;

import com.transaction.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRespository extends JpaRepository<Table, String> {
}
