package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.repository.AccountRepository;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.usecase.factory.TransactionFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;
    final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionEntity register(TransactionRequest transactionRequest) {
        AccountEntity accountEntity = getAccountEntityById(transactionRequest.getAccountId());
        TransactionFactory transactionFactory = new TransactionFactory();
        return transactionRepository.save(transactionFactory.registerTransaction(transactionRequest, accountEntity));
    }

    private AccountEntity getAccountEntityById(long id) {
        final Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        if (accountEntity.isEmpty()) {
            throw new AccountNotExistException("This account id: " + id + " doesn't exist");
        }
        return accountEntity.get();
    }
}
