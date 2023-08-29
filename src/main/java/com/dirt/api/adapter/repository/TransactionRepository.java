package com.dirt.api.adapter.repository;

import com.dirt.api.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>, QuerydslPredicateExecutor<TransactionEntity> {
}
