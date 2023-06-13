package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.StatusEnum;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TransactionFactory {

    public TransactionEntity registerTransaction(TransactionRequest transactionRequest) {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionDat(Timestamp.valueOf(dateTime));
        transactionEntity.setStatus(StatusEnum.PENDING);
        return transactionEntity;
    }
}
