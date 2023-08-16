package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.repository.AccountRepository;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.domain.exception.StatusValidateException;
import com.dirt.api.domain.exception.TransactionNotExistException;
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

    public TransactionEntity registerTransaction(TransactionRequest transactionRequest) {
        final AccountEntity accountEntity = getAccountEntityById(transactionRequest.getAccountId());
        return transactionRepository.save(TransactionFactory.createTransaction(transactionRequest, accountEntity));
    }

    public TransactionEntity updateTransactionStatusById(long transactionId, UpdateStatusRequest updateStatusRequest) {
        final TransactionEntity transactionEntity = getTransactionEntityById(transactionId);
        validateStatusTransaction(transactionEntity.getStatus());
        return transactionRepository.save(updateTransactionStatus(updateStatusRequest, transactionEntity));
    }

    public void deleteTransaction(long transactionId) {
        final TransactionEntity transactionEntity = getTransactionEntityById(transactionId);
        transactionRepository.delete(transactionEntity);
    }

    public TransactionEntity getTransactionById(long transactionId) {
        return getTransactionEntityById(transactionId);
    }

    private AccountEntity getAccountEntityById(long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotExistException("This account id: " + accountId + " doesn't exist"));
    }

    private TransactionEntity getTransactionEntityById(long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotExistException("This transaction id: " + transactionId + " doesn't exist"));
    }

    private TransactionEntity updateTransactionStatus(UpdateStatusRequest updateStatusRequest, TransactionEntity transactionEntity) {
        transactionEntity.setStatus(StatusEnum.fromValue(updateStatusRequest.getStatus()));

        return transactionEntity;
    }

    private void validateStatusTransaction(StatusEnum currentStatus) {
        if (currentStatus == StatusEnum.SUCCESS || currentStatus == StatusEnum.CANCELED) {
            throw new StatusValidateException("Cannot update " + currentStatus + " transaction");
        }
    }
}
