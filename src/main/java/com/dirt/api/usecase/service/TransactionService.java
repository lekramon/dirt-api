package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.Transaction;
import com.dirt.api.usecase.factory.TransactionFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction register(TransactionRequest transactionRequest) {
        TransactionFactory transactionFactory = new TransactionFactory();
        return transactionRepository.save(transactionFactory.registerTransaction(transactionRequest));
    }
}
