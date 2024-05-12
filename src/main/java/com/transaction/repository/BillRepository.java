package com.transaction.repository;

import com.transaction.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
}
