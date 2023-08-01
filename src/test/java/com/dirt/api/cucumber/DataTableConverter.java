package com.dirt.api.cucumber;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.response.AccountResponse;
import com.dirt.api.adapter.dto.response.TransactionResponse;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import io.cucumber.java.DataTableType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

public class DataTableConverter {

    @DataTableType
    public static TransactionRequest convertToTransactionRequest(Map<String, String> entry) {
        final TransactionRequest transactionRequest = new TransactionRequest();
        final CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        final OtherAccountDto otherAccountDto = new OtherAccountDto();

        transactionRequest.setIp(entry.get("ip"));
        transactionRequest.setAmount(new BigDecimal(entry.get("amount")));
        transactionRequest.setTax(new BigDecimal(entry.get("tax")));
        transactionRequest.setAccountId(Long.parseLong(entry.get("accountId")));
        transactionRequest.setDescription(entry.get("description"));
        captureMethodDto.setCaptureMethodId(entry.get("captureMethodId"));
        captureMethodDto.setType(entry.get("captureMethodType"));
        transactionRequest.setCaptureMethod(captureMethodDto);
        transactionRequest.setTransactionType(entry.get("transactionType"));
        transactionRequest.setOperation(entry.get("operation"));
        otherAccountDto.setAccountNumber(entry.get("otherAccountNumber"));
        otherAccountDto.setAccountAgency(entry.get("otherAccountAgency"));
        otherAccountDto.setAccountBankCode(entry.get("otherAccountBankCode"));
        transactionRequest.setOtherAccount(otherAccountDto);

        return transactionRequest;
    }

    @DataTableType
    public static TransactionResponse convertToTransactionResponse(Map<String, String> entry) {
        final TransactionResponse transactionResponse = new TransactionResponse();
        final AccountResponse accountResponse = new AccountResponse();
        final CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        final OtherAccountDto otherAccountDto = new OtherAccountDto();

        transactionResponse.setTransactionId(Long.valueOf(entry.get("transactionId")));
        transactionResponse.setTransactionIp(entry.get("transactionIp"));
        transactionResponse.setTransactionAmount(new BigDecimal(entry.get("transactionAmount")));
        transactionResponse.setTransactionTax(new BigDecimal(entry.get("transactionTax")));
        accountResponse.setAccountId(Long.valueOf(entry.get("accountId")));
        accountResponse.setAccountName(entry.get("accountName"));
        accountResponse.setDocument(entry.get("accountDocument"));
        accountResponse.setAccountNum(entry.get("accountNum"));
        accountResponse.setAccountNumAgency(entry.get("accountNumAgency"));
        accountResponse.setAccountNumBank(entry.get("accountNumBank"));
        accountResponse.setAccountType(entry.get("accountType").charAt(0));
        transactionResponse.setAccount(accountResponse);
        transactionResponse.setDescription(entry.get("description"));
        captureMethodDto.setCaptureMethodId(entry.get("captureMethodId"));
        captureMethodDto.setType(entry.get("captureMethodType"));
        transactionResponse.setCaptureMethod(captureMethodDto);
        transactionResponse.setTransactionType(entry.get("transactionType"));
        transactionResponse.setOperation(entry.get("operation"));
        transactionResponse.setStatus(entry.get("status"));
        otherAccountDto.setAccountNumber(entry.get("otherAccountNumber"));
        otherAccountDto.setAccountAgency(entry.get("otherAccountAgency"));
        otherAccountDto.setAccountBankCode(entry.get("otherAccountBankCode"));
        transactionResponse.setOtherAccount(otherAccountDto);

        return transactionResponse;
    }

    @DataTableType
    public static TransactionEntity convertToTransactionEntity(Map<String, String> entry) {
        final TransactionEntity transactionEntity = new TransactionEntity();
        final AccountEntity accountEntity = new AccountEntity();

        transactionEntity.setTransactionId(Long.valueOf(entry.get("transactionId")));
        transactionEntity.setTransactionIp(entry.get("transactionIp"));
        transactionEntity.setTransactionAmount(new BigDecimal(entry.get("transactionAmount")));
        transactionEntity.setTransactionTax(new BigDecimal(entry.get("transactionTax")));
        accountEntity.setAccountId(Long.valueOf(entry.get("accountId")));
        accountEntity.setAccountName(entry.get("accountName"));
        accountEntity.setDocument(entry.get("accountDocument"));
        accountEntity.setAccountNum(entry.get("accountNum"));
        accountEntity.setAccountNumAgency(entry.get("accountNumAgency"));
        accountEntity.setAccountNumBank(entry.get("accountNumBank"));
        accountEntity.setAccountType(entry.get("accountType").charAt(0));
        transactionEntity.setTransactionAccount(accountEntity);
        transactionEntity.setTransactionDes(entry.get("description"));
        transactionEntity.setTransactionCaptureMethod(entry.get("captureMethodId"));
        transactionEntity.setCaptureMethod(CaptureMethodEnum.fromValue(entry.get("captureMethodType")));
        transactionEntity.setTransactionType(TransactionTypeEnum.fromValue(entry.get("transactionType")));
        transactionEntity.setOperation(OperationEnum.fromValue(entry.get("operation")));
        transactionEntity.setTransactionDat(Timestamp.valueOf(entry.get("transactionDat")));
        transactionEntity.setStatus(StatusEnum.fromValue(entry.get("status")));
        transactionEntity.setTransactionOtherAccount(entry.get("otherAccountNumber"));
        transactionEntity.setTransactionOtherAccountAgency(entry.get("otherAccountAgency"));
        transactionEntity.setTransactionOtherAccountBank(entry.get("otherAccountBankCode"));

        return transactionEntity;
    }

}
