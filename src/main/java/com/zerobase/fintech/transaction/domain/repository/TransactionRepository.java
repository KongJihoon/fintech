package com.zerobase.fintech.transaction.domain.repository;

import com.zerobase.fintech.transaction.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
