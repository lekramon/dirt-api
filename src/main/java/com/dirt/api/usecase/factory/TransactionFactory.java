package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;

import java.sql.Timestamp;

public class TransactionFactory {

    public static TransactionEntity createTransaction(TransactionRequest transactionRequest, AccountEntity accountEntity) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TransactionEntity transactionEntity = new TransactionEntity();
        CaptureMethodDto captureMethodDto = transactionRequest.getCaptureMethod();
        OtherAccountDto otherAccountDto = transactionRequest.getOtherAccount();
        transactionEntity.setTransactionIp(transactionRequest.getIp());
        transactionEntity.setTransactionAmount(transactionRequest.getAmount());
        transactionEntity.setTransactionTax(transactionRequest.getTax());
        transactionEntity.setTransactionAccount(accountEntity);
        transactionEntity.setTransactionDes(transactionRequest.getDescription());
        transactionEntity.setTransactionCaptureMethod(captureMethodDto.getCaptureMethodId());
        transactionEntity.setCaptureMethod(CaptureMethodEnum.fromValue(captureMethodDto.getType()));
        transactionEntity.setTransactionType(TransactionTypeEnum.fromValue(transactionRequest.getTransactionType()));
        transactionEntity.setOperation(OperationEnum.fromValue(transactionRequest.getOperation()));
        transactionEntity.setTransactionDat(timestamp);
        transactionEntity.setTransactionOtherAccount(otherAccountDto.getAccountNumber());
        transactionEntity.setTransactionOtherAccountAgency(otherAccountDto.getAccountAgency());
        transactionEntity.setTransactionOtherAccountBank(otherAccountDto.getAccountBankCode());
        transactionEntity.setStatus(StatusEnum.fromCode(1));
        return transactionEntity;
    }
}
