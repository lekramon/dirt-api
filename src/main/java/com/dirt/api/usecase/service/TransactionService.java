package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.usecase.factory.TransactionFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionEntity register(TransactionRequest transactionRequest) {
        TransactionFactory transactionFactory = new TransactionFactory();
        return transactionRepository.save(transactionFactory.registerTransaction(transactionRequest));
    }
}
