package com.dirt.api.usecase.factory;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.response.AccountResponse;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.domain.entity.TransactionEntity;

public class TransactionResponseFactory {

    private TransactionResponseFactory() {

    }

    public static TransactionResponse createTransactionResponse(TransactionEntity transactionEntity) {
        final TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(transactionEntity.getTransactionId());
        transactionResponse.setStatus(transactionEntity.getStatus().name());
        transactionResponse.setTransactionIp(transactionEntity.getTransactionIp());
        transactionResponse.setTransactionAmount(transactionEntity.getTransactionAmount());
        transactionResponse.setTransactionTax(transactionEntity.getTransactionTax());
        transactionResponse.setDescription(transactionEntity.getTransactionDes());
        transactionResponse.setTransactionType(transactionEntity.getTransactionType().name());
        transactionResponse.setOperation(transactionEntity.getOperation().name());

        final AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(transactionEntity.getTransactionAccount().getAccountId());
        accountResponse.setAccountName(transactionEntity.getTransactionAccount().getAccountName());
        accountResponse.setDocument(transactionEntity.getTransactionAccount().getDocument());
        accountResponse.setAccountNum(transactionEntity.getTransactionAccount().getAccountNum());
        accountResponse.setAccountNumAgency(transactionEntity.getTransactionAccount().getAccountNumAgency());
        accountResponse.setAccountNumBank(transactionEntity.getTransactionAccount().getAccountNumBank());
        accountResponse.setAccountType(transactionEntity.getTransactionAccount().getAccountType());
        transactionResponse.setAccount(accountResponse);

        final CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        captureMethodDto.setCaptureMethodId(transactionEntity.getTransactionCaptureMethod());
        captureMethodDto.setType(transactionEntity.getCaptureMethod().name());
        transactionResponse.setCaptureMethod(captureMethodDto);

        final OtherAccountDto otherAccountDto = new OtherAccountDto();
        otherAccountDto.setAccountNumber(transactionEntity.getTransactionOtherAccount());
        otherAccountDto.setAccountAgency(transactionEntity.getTransactionOtherAccountAgency());
        otherAccountDto.setAccountBankCode(transactionEntity.getTransactionOtherAccountBank());
        transactionResponse.setOtherAccount(otherAccountDto);

        return transactionResponse;
    }
}