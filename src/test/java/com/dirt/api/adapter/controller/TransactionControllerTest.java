package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.dto.response.AccountResponse;
import com.dirt.api.adapter.dto.response.TransactionResponse;
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

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    private static final BigDecimal TRANSACTION_AMOUNT = BigDecimal.valueOf(100.0);
    private static final BigDecimal TRANSACTION_TAX = BigDecimal.valueOf(3.0);
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
        when(transactionService.registerTransaction(any(TransactionRequest.class))).thenReturn(getTransactionEntity(StatusEnum.PENDING));

        final ResponseEntity<TransactionResponse> actualTransactionResponse = transactionController.registerTransaction(getTransactionRequest());
        final ResponseEntity<TransactionResponse> expectedTransactionResponse = getResponseEntityTransaction(getTransactionResponse("PENDING"), HttpStatus.CREATED);

        Assertions.assertEquals(expectedTransactionResponse.getStatusCode(), actualTransactionResponse.getStatusCode());
        assertThat(actualTransactionResponse.getBody()).usingRecursiveComparison().isEqualTo(expectedTransactionResponse.getBody());
    }

    @Test
    public void shouldUpdateStatusTransaction() {
        when(transactionService.updateTransactionStatusById(anyLong(), any(UpdateStatusRequest.class))).thenReturn(getTransactionEntity(StatusEnum.SUCCESS));

        final ResponseEntity<TransactionResponse> actualTransactionResponse = transactionController.updateTransactionStatus(1L, getUpdateStatusRequest("SUCCESS"));
        final ResponseEntity<TransactionResponse> expectedTransactionResponse = getResponseEntityTransaction(getTransactionResponse("SUCCESS"), HttpStatus.OK);

        Assertions.assertEquals(expectedTransactionResponse.getStatusCode(), actualTransactionResponse.getStatusCode());
        assertThat(actualTransactionResponse.getBody()).usingRecursiveComparison().isEqualTo(expectedTransactionResponse.getBody());
    }

    private ResponseEntity<TransactionResponse> getResponseEntityTransaction(TransactionResponse transactionResponse, HttpStatus status) {
        return ResponseEntity.status(status).body(transactionResponse);
    }

    private AccountEntity getAccountEntity() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(ACCOUNT_ID);
        accountEntity.setAccountName(ACCOUNT_NAME);
        accountEntity.setDocument(ACCOUNT_DOCUMENT);
        accountEntity.setAccountNum(ACCOUNT_NUM);
        accountEntity.setAccountNumAgency(ACCOUNT_NUM_AGENCY);
        accountEntity.setAccountNumBank(ACCOUNT_NUM_BANK);
        accountEntity.setAccountType(ACCOUNT_TYPE);
        return accountEntity;
    }

    private TransactionEntity getTransactionEntity(StatusEnum status) {
        final TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
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
        transactionEntity.setStatus(status);
        transactionEntity.setTransactionOtherAccount(TRANSACTION_OTHER_ACCOUNT);
        transactionEntity.setTransactionOtherAccountAgency(TRANSACTION_OTHER_ACCOUNT_AGENCY);
        transactionEntity.setTransactionOtherAccountBank(TRANSACTION_OTHER_ACCOUNT_BANK);
        return transactionEntity;
    }

    private TransactionRequest getTransactionRequest() {
        final CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        captureMethodDto.setCaptureMethodId(TRANSACTION_CAPTURE_METHOD);
        captureMethodDto.setType("APP");

        final OtherAccountDto otherAccountDto = new OtherAccountDto();
        otherAccountDto.setAccountNumber(TRANSACTION_OTHER_ACCOUNT);
        otherAccountDto.setAccountAgency(TRANSACTION_OTHER_ACCOUNT_AGENCY);
        otherAccountDto.setAccountBankCode(TRANSACTION_OTHER_ACCOUNT_BANK);

        final TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(ACCOUNT_ID);
        transactionRequest.setIp(TRANSACTION_IP);
        transactionRequest.setAmount(TRANSACTION_AMOUNT);
        transactionRequest.setTax(TRANSACTION_TAX);
        transactionRequest.setDescription(DES_PAGAMENTO);
        transactionRequest.setCaptureMethod(captureMethodDto);
        transactionRequest.setTransactionType("PIX");
        transactionRequest.setOperation("DEBIT");
        transactionRequest.setOtherAccount(otherAccountDto);
        return transactionRequest;
    }

    private TransactionResponse getTransactionResponse(String status) {
        final TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(1L);
        transactionResponse.setStatus(status);
        transactionResponse.setTransactionIp(getTransactionRequest().getIp());
        transactionResponse.setTransactionAmount(getTransactionRequest().getAmount());
        transactionResponse.setTransactionTax(getTransactionRequest().getTax());
        transactionResponse.setDescription(getTransactionRequest().getDescription());
        transactionResponse.setTransactionType(getTransactionRequest().getTransactionType());
        transactionResponse.setOperation(getTransactionRequest().getOperation());
        transactionResponse.setAccount(getAccountResponse());
        transactionResponse.setCaptureMethod(getTransactionRequest().getCaptureMethod());
        transactionResponse.setOtherAccount(getTransactionRequest().getOtherAccount());

        return transactionResponse;
    }

    private AccountResponse getAccountResponse() {
        final AccountResponse accountResponse = new AccountResponse();

        accountResponse.setAccountId(ACCOUNT_ID);
        accountResponse.setDocument(ACCOUNT_DOCUMENT);
        accountResponse.setAccountName(ACCOUNT_NAME);
        accountResponse.setAccountNum(ACCOUNT_NUM);
        accountResponse.setAccountNumAgency(ACCOUNT_NUM_AGENCY);
        accountResponse.setAccountNumBank(ACCOUNT_NUM_BANK);
        accountResponse.setAccountType(ACCOUNT_TYPE);

        return accountResponse;
    }

    private UpdateStatusRequest getUpdateStatusRequest(String status) {
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        updateStatusRequest.setStatus(status);

        return updateStatusRequest;
    }
}