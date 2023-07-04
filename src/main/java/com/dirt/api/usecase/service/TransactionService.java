package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.repository.AccountRepository;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.usecase.factory.TransactionFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionEntity register(TransactionRequest transactionRequest) {
        final AccountEntity accountEntity = getAccountEntityById(transactionRequest.getAccountId());
        return transactionRepository.save(TransactionFactory.createTransaction(transactionRequest, accountEntity));
    }

    private AccountEntity getAccountEntityById(long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotExistException("This account id: " + id + " doesn't exist"));
    }
}
