package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.request.CaptureMethodRequest;
import com.dirt.api.adapter.dto.request.OtherAccountRequest;
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
        CaptureMethodRequest captureMethodRequest = transactionRequest.getCaptureMethodRequest();
        OtherAccountRequest otherAccountRequest = transactionRequest.getOtherAccountRequest();
        transactionEntity.setTransactionIp(transactionRequest.getIp());
        transactionEntity.setTransactionAmount(transactionRequest.getAmount());
        transactionEntity.setTransactionTax(transactionRequest.getTax());
        transactionEntity.setTransactionAccount(transactionRequest.getAccountId());
        transactionEntity.setTransactionCaptureMethod(captureMethodRequest.getId());
        transactionEntity.setCaptureMethod(captureMethodRequest.getType());
        transactionEntity.setTransactionType(transactionRequest.getTransactionType());
        transactionEntity.setOperation(transactionRequest.getOperation());
        transactionEntity.setTransactionDat(Timestamp.valueOf(dateTime));
        transactionEntity.setStatus(StatusEnum.PENDING);
        transactionEntity.setTransactionOtherAccount(otherAccountRequest.getNumber());
        transactionEntity.setTransactionOtherAccountAgency(otherAccountRequest.getAgency());
        transactionEntity.setTransactionOtherAccountBank(otherAccountRequest.getBankCode());
        return transactionEntity;
    }
}
