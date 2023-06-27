package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.request.CaptureMethodDto;
import com.dirt.api.adapter.dto.request.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.repository.AccountRepository;
import com.dirt.api.adapter.repository.TransactionRepository;
import com.dirt.api.domain.entity.AccountEntity;
import com.dirt.api.domain.entity.TransactionEntity;
import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;
import com.dirt.api.domain.exception.AccountNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    private static final Double TRANSACTION_AMOUNT = 100.0;
    private static final Double TRANSACTION_TAX = 3.0;
    private static final String TRANSACTION_CAPTURE_METHOD = "123456";
    private static final CaptureMethodEnum CAPTURE_METHOD = CaptureMethodEnum.APP;
    private static final TransactionTypeEnum TRANSACTION_TYPE = TransactionTypeEnum.PIX;
    private static final OperationEnum OPERATION = OperationEnum.DEBIT;
    private static final String TRANSACTION_OTHER_ACCOUNT = "123456789";
    private static final String TRANSACTION_OTHER_ACCOUNT_AGENCY = "0001";
    private static final String TRANSACTION_OTHER_ACCOUNT_BANK = "290";

    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final TransactionService transactionService = new TransactionService(transactionRepository, accountRepository);

    @Test
    public void shouldRegisterTransaction() {
        when(accountRepository.findById(getAccountEntity().getAccountId())).thenReturn(Optional.of(getAccountEntity()));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(getTransactionEntity());

        TransactionEntity actualTransactionEntity = transactionService.register(getTransactionRequest(ACCOUNT_ID, 1));
        TransactionEntity expectedTransactionEntity = getTransactionEntity();

        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(expectedTransactionEntity);

    }

    @Test
    public void shouldThrowAccountNotExistException() {
        when(accountRepository.findById(NOT_ACCOUNT_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(AccountNotExistException.class, () -> {
            transactionService.register(getTransactionRequest(NOT_ACCOUNT_ID, 1));
        });
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

    private TransactionRequest getTransactionRequest(long accountId, int operation) {
        CaptureMethodDto captureMethodDto = new CaptureMethodDto();
        captureMethodDto.setCaptureMethodId(TRANSACTION_CAPTURE_METHOD);
        captureMethodDto.setType(1);

        OtherAccountDto otherAccountDto = new OtherAccountDto();
        otherAccountDto.setAccountNumber("1234");
        otherAccountDto.setAccountAgency("1234");
        otherAccountDto.setAccountBankCode("1234");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAccountId(accountId);
        transactionRequest.setIp(TRANSACTION_IP);
        transactionRequest.setAmount(TRANSACTION_AMOUNT);
        transactionRequest.setTax(TRANSACTION_TAX);
        transactionRequest.setDescription(DES_PAGAMENTO);
        transactionRequest.setCaptureMethod(captureMethodDto);
        transactionRequest.setTransactionType(1);
        transactionRequest.setOperation(operation);
        transactionRequest.setOtherAccount(otherAccountDto);
        return transactionRequest;
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

}