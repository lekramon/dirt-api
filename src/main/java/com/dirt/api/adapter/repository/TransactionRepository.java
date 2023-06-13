package com.dirt.api.adapter.repository;

import com.dirt.api.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface TransactionRepository extends JpaRepository<Transaction, Id> {
}
