package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.request.CaptureMethodDto;
import com.dirt.api.adapter.dto.request.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.StatusEnum;

import java.sql.Timestamp;

public class TransactionFactory {

    public TransactionEntity registerTransaction(TransactionRequest transactionRequest, AccountEntity accountEntity) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TransactionEntity transactionEntity = new TransactionEntity();
        CaptureMethodDto captureMethodDto = transactionRequest.getCaptureMethodRequest();
        OtherAccountDto otherAccountDto = transactionRequest.getOtherAccountRequest();
        transactionEntity.setTransactionIp(transactionRequest.getIp());
        transactionEntity.setTransactionAmount(transactionRequest.getAmount());
        transactionEntity.setTransactionTax(transactionRequest.getTax());
        transactionEntity.setTransactionAccount(accountEntity);
        transactionEntity.setTransactionDes(transactionRequest.getDescription());
        transactionEntity.setTransactionCaptureMethod(captureMethodDto.getId());
        transactionEntity.setCaptureMethod(captureMethodDto.getType());
        transactionEntity.setTransactionType(transactionRequest.getTransactionType());
        transactionEntity.setOperation(transactionRequest.getOperation());
        transactionEntity.setTransactionDat(timestamp);
        transactionEntity.setStatus(StatusEnum.PENDING);
        transactionEntity.setTransactionOtherAccount(otherAccountDto.getNumber());
        transactionEntity.setTransactionOtherAccountAgency(otherAccountDto.getAgency());
        transactionEntity.setTransactionOtherAccountBank(otherAccountDto.getBankCode());
        return transactionEntity;
    }
}
