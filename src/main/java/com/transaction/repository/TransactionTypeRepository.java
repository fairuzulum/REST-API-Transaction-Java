package com.transaction.repository;


import com.transaction.constan.TransType;
import com.transaction.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, String> {
   TransactionType findByDescription(TransType description);
}
