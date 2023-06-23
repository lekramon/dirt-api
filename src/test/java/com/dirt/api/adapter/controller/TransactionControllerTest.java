package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.request.CaptureMethodDto;
import com.dirt.api.adapter.dto.request.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.usecase.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    private static final Long ACCOUNT_ID = 1L;
    private static final String ACCOUNT_NAME = "Han Solo";
    private static final String ACCOUNT_DOCUMENT = "59805714004";
    private static final String ACCOUNT_NUM = "5840231-7";
    private static final String ACCOUNT_NUM_AGENCY = "0001";
    private static final String ACCOUNT_NUM_BANK = "290";
    private static final char ACCOUNT_TYPE = 'F';

    private static final String DES_PAGAMENTO = "Payment Ze Manga";
    private static final String TRANSACTION_IP = "192.168.0.1";
    private static final Double TRANSACTION_AMOUNT = 100.0;
    private static final Double TRANSACTION_TAX = 3.0;
    private static final String TRANSACTION_CAPTURE_METHOD = "123456";
    private static final CaptureMethodEnum CAPTURE_METHOD = CaptureMethodEnum.APP;
    private static final TransactionTypeEnum TRANSACTION_TYPE = TransactionTypeEnum.PIX;
    private static final OperationEnum OPERATION = OperationEnum.DEBIT;
    private static final String TRANSACTION_OTHER_ACCOUNT = "123456789";
    private static final String TRANSACTION_OTHER_ACCOUNT_AGENCY = "0001";
    private static final String TRANSACTION_OTHER_ACCOUNT_BANK = "290";

    private final TransactionService transactionService = mock(TransactionService.class);

    private final TransactionController transactionController = new TransactionController(transactionService);

    @Test
    public void shouldRegisterTransaction() {
        when(transactionService.register(any(TransactionRequest.class))).thenReturn(getTransactionEntity());

        ResponseEntity<TransactionEntity> actualTransactionResponseEntity = transactionController.registerTransaction(getTransactionRequest());
        ResponseEntity<TransactionEntity> expectedTransactionResponseEntity = getResponseEntityTransaction();

        Assertions.assertEquals(expectedTransactionResponseEntity.getStatusCode(), actualTransactionResponseEntity.getStatusCode());
        assertThat(actualTransactionResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedTransactionResponseEntity.getBody());
    }

    private ResponseEntity<TransactionEntity> getResponseEntityTransaction() {
        TransactionEntity transactionEntity = getTransactionEntity();
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionEntity);
    }

    private AccountEntity getAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(ACCOUNT_ID);
        accountEntity.setAccountName(ACCOUNT_NAME);
        accountEntity.setDocument(ACCOUNT_DOCUMENT);
        accountEntity.setAccountNum(ACCOUNT_NUM);
        accountEntity.setAccountNumAgency(ACCOUNT_NUM_AGENCY);
        accountEntity.setAccountNumBank(ACCOUNT_NUM_BANK);
        accountEntity.setAccountType(ACCOUNT_TYPE);
        return accountEntity;
    }

    private TransactionEntity getTransactionEntity() {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionAccount(getAccountEntity());
        transactionEntity.setTransactionIp(TRANSACTION_IP);
        transactionEntity.setTransactionAmount(TRANSACTION_AMOUNT);
        transactionEntity.setTransactionTax(TRANSACTION_TAX);
        transactionEntity.setTransactionDes(DES_PAGAMENTO);
        transactionEntity.setTransactionCaptureMethod(TRANSACTION_CAPTURE_METHOD);
        transactionEntity.setCaptureMethod(CAPTURE_METHOD);
        transactionEntity.setTransactionType(TRANSACTION_TYPE);
        transactionEntity.setOperation(OPERATION);
        transactionEntity.setTransactionDat(Timestamp.valueOf("2023-06-22 13:10:46.735"));
        transactionEntity.setStatus(StatusEnum.PENDING);
        transactionEntity.setTransactionOtherAccount(TRANSACTION_OTHER_ACCOUNT);
        transactionEntity.setTransactionOtherAccountAgency(TRANSACTION_OTHER_ACCOUNT_AGENCY);
        transactionEntity.setTransactionOtherAccountBank(TRANSACTION_OTHER_ACCOUNT_BANK);
        return transactionEntity;
    }

    private TransactionRequest getTransactionRequest() {
        CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        captureMethodDto.setId("1234");
        captureMethodDto.setType(1);

        OtherAccountDto otherAccountDto = new OtherAccountDto();
        otherAccountDto.setNumber("1234");
        otherAccountDto.setAgency("1234");
        otherAccountDto.setBankCode("1234");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(ACCOUNT_ID);
        transactionRequest.setIp(TRANSACTION_IP);
        transactionRequest.setAmount(TRANSACTION_AMOUNT);
        transactionRequest.setTax(TRANSACTION_TAX);
        transactionRequest.setDescription(DES_PAGAMENTO);
        transactionRequest.setCaptureMethodRequest(captureMethodDto);
        transactionRequest.setTransactionType(1);
        transactionRequest.setOperation(1);
        transactionRequest.setOtherAccountRequest(otherAccountDto);
        return transactionRequest;
    }


}