package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.UpdateStatusRequest;
import com.dirt.api.adapter.repository.AccountRepository;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.domain.exception.StatusValidateException;
import com.dirt.api.domain.exception.TransactionNotExistException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TransactionServiceTest {

    private static final Long ACCOUNT_ID = 1L;
    private static final Long NOT_ACCOUNT_ID = 999L;
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
    private static final String TRANSACTION_NOT_EXIST_MESSAGE = "This transaction id: 3 doesn't exist";
    private static final String INVALID_STATUS_CANCELED_CHANGE_MESSAGE = "Cannot update status from CANCELED to SUCCESS";
    private static final String INVALID_STATUS_SUCCESS_CHANGE_MESSAGE = "Cannot update status from SUCCESS to PENDING";
    private static final String ACCOUNT_NOT_EXIST_MESSAGE = "This account id: 999 doesn't exist";

    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final TransactionService transactionService = new TransactionService(transactionRepository, accountRepository);

    @Test
    public void shouldRegisterTransaction() {
        when(accountRepository.findById(getAccountEntity().getAccountId())).thenReturn(Optional.of(getAccountEntity()));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(getTransactionEntity(StatusEnum.PENDING));

        final TransactionEntity actualTransactionEntity = transactionService.registerTransaction(getTransactionRequest(ACCOUNT_ID, "CREDIT"));
        final TransactionEntity expectedTransactionEntity = getTransactionEntity(StatusEnum.PENDING);

        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(expectedTransactionEntity);
    }

    @Test
    public void shouldUpdateTransactionStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.PENDING)));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(getTransactionEntity(StatusEnum.SUCCESS));

        final TransactionEntity actualTransactionEntity = transactionService.updateTransactionStatusById(1, getUpdateStatusRequest("SUCCESS"));
        final TransactionEntity expectedTransactionEntity = getTransactionEntity(StatusEnum.SUCCESS);

        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(expectedTransactionEntity);
    }

    @Test
    public void shouldThrowAccountNotExistException() {
        when(accountRepository.findById(NOT_ACCOUNT_ID)).thenReturn(Optional.empty());

        final TransactionRequest transactionRequest = getTransactionRequest(NOT_ACCOUNT_ID, "DEBIT");

        final AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class, () -> {
            transactionService.registerTransaction(transactionRequest);
        });
        assertEquals(ACCOUNT_NOT_EXIST_MESSAGE, accountNotExistException.getMessage());
    }

    @Test
    public void shouldThrowTransactionNotExistException() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());

        final UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        final TransactionNotExistException transactionNotExistException = assertThrows(TransactionNotExistException.class, () -> {
            transactionService.updateTransactionStatusById(3L, updateStatusRequest);
        });
        assertEquals(TRANSACTION_NOT_EXIST_MESSAGE, transactionNotExistException.getMessage());
    }

    @Test
    public void shouldThrowStatusValidateExceptionForCanceledStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.CANCELED)));

        final UpdateStatusRequest updateStatusRequest = getUpdateStatusRequest("SUCCESS");

        final StatusValidateException statusValidateExceptionSuccess = assertThrows(StatusValidateException.class, () -> {
            transactionService.updateTransactionStatusById(1L, updateStatusRequest);
        });

        assertEquals(INVALID_STATUS_CANCELED_CHANGE_MESSAGE, statusValidateExceptionSuccess.getMessage());
    }

    @Test
    public void shouldThrowStatusValidateExceptionForSuccessStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.SUCCESS)));

        final UpdateStatusRequest updateStatusRequest = getUpdateStatusRequest("PENDING");

        final StatusValidateException statusValidateExceptionSuccess = assertThrows(StatusValidateException.class, () -> {
            transactionService.updateTransactionStatusById(1L, updateStatusRequest);
        });

        assertEquals(INVALID_STATUS_SUCCESS_CHANGE_MESSAGE, statusValidateExceptionSuccess.getMessage());
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

    private TransactionRequest getTransactionRequest(long accountId, String operation) {
        final CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        captureMethodDto.setCaptureMethodId(TRANSACTION_CAPTURE_METHOD);
        captureMethodDto.setType("WEB");

        final OtherAccountDto otherAccountDto = new OtherAccountDto();
        otherAccountDto.setAccountNumber("1234");
        otherAccountDto.setAccountAgency("1234");
        otherAccountDto.setAccountBankCode("1234");

        final TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(accountId);
        transactionRequest.setIp(TRANSACTION_IP);
        transactionRequest.setAmount(TRANSACTION_AMOUNT);
        transactionRequest.setTax(TRANSACTION_TAX);
        transactionRequest.setDescription(DES_PAGAMENTO);
        transactionRequest.setCaptureMethod(captureMethodDto);
        transactionRequest.setTransactionType("PIX");
        transactionRequest.setOperation(operation);
        transactionRequest.setOtherAccount(otherAccountDto);
        return transactionRequest;
    }

    private TransactionEntity getTransactionEntity(StatusEnum status) {
        final TransactionEntity transactionEntity = new TransactionEntity();
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

    private UpdateStatusRequest getUpdateStatusRequest(String status) {
        UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        updateStatusRequest.setStatus(status);

        return updateStatusRequest;
    }
}