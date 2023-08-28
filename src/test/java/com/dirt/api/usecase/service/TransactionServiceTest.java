package com.dirt.api.usecase.service;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;
import com.dirt.api.adapter.dto.request.TransactionRequest;
import com.dirt.api.adapter.dto.request.TransactionSearch;
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
import com.dirt.api.domain.exception.InvalidPageException;
import com.dirt.api.domain.exception.StatusValidateException;
import com.dirt.api.domain.exception.TransactionNotExistException;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    private static final String INVALID_STATUS_CANCELED_CHANGE_MESSAGE = "Cannot update CANCELED transaction";
    private static final String INVALID_STATUS_SUCCESS_CHANGE_MESSAGE = "Cannot update SUCCESS transaction";
    private static final String ACCOUNT_NOT_EXIST_MESSAGE = "This account id: 999 doesn't exist";
    private static final String INVALID_PAGE_MESSAGE = "Page index must not be less than zero";
    private static final int SIZE = 1;
    private static final long TOTAL_SIZE = 1;
    private static final int PAGE = 0;
    private static final int TOTAL_PAGES = 1;

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
        verify(accountRepository, times(1)).findById(ACCOUNT_ID);
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    public void shouldUpdateTransactionStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.PENDING)));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(getTransactionEntity(StatusEnum.SUCCESS));

        final TransactionEntity actualTransactionEntity = transactionService.updateTransactionStatusById(1, getUpdateStatusRequest("SUCCESS"));
        final TransactionEntity expectedTransactionEntity = getTransactionEntity(StatusEnum.SUCCESS);

        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(expectedTransactionEntity);
        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    public void shouldDeleteTransaction() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.CANCELED)));
        doNothing().when(transactionRepository).delete(any(TransactionEntity.class));

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).delete(any(TransactionEntity.class));
    }

    @Test
    public void shouldGetTransactionById() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.SUCCESS)));

        final TransactionEntity actualTransactionEntity = transactionService.getTransactionById(1L);
        final TransactionEntity expectedTransactionEntity = getTransactionEntity(StatusEnum.SUCCESS);

        assertThat(actualTransactionEntity).usingRecursiveComparison().isEqualTo(expectedTransactionEntity);
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldGetTransactionList() {
        when(transactionRepository.findAll(any(BooleanExpression.class), any(Pageable.class))).thenReturn(getTransactionPage());

        final Page<TransactionEntity> actualTransactionEntityPage = transactionService.getTransactionList(PAGE, getTransactionSearch());
        final Page<TransactionEntity> expectedTransactionEntityPage = getTransactionPage();

        assertThat(actualTransactionEntityPage).usingRecursiveComparison().isEqualTo(expectedTransactionEntityPage);
        verify(transactionRepository, times(1)).findAll(any(BooleanExpression.class), any(Pageable.class));
    }

    @Test
    public void shouldThrowAccountNotExistException() {
        when(accountRepository.findById(NOT_ACCOUNT_ID)).thenReturn(Optional.empty());

        final TransactionRequest transactionRequest = getTransactionRequest(NOT_ACCOUNT_ID, "DEBIT");

        final AccountNotExistException accountNotExistException = assertThrows(AccountNotExistException.class, () -> {
            transactionService.registerTransaction(transactionRequest);
        });

        assertEquals(ACCOUNT_NOT_EXIST_MESSAGE, accountNotExistException.getMessage());
        verify(accountRepository, times(1)).findById(NOT_ACCOUNT_ID);
    }

    @Test
    public void shouldThrowTransactionNotExistException() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());

        final UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        final TransactionNotExistException transactionNotExistExceptionToUpdateStatus = assertThrows(TransactionNotExistException.class, () -> {
            transactionService.updateTransactionStatusById(3L, updateStatusRequest);
        });
        final TransactionNotExistException transactionNotExistExceptionToDelete = assertThrows(TransactionNotExistException.class, () -> {
            transactionService.deleteTransaction(3L);
        });
        final TransactionNotExistException transactionNotExistExceptionToGetById = assertThrows(TransactionNotExistException.class, () -> {
            transactionService.getTransactionById(3L);
        });

        assertEquals(TRANSACTION_NOT_EXIST_MESSAGE, transactionNotExistExceptionToUpdateStatus.getMessage());
        assertEquals(TRANSACTION_NOT_EXIST_MESSAGE, transactionNotExistExceptionToDelete.getMessage());
        assertEquals(TRANSACTION_NOT_EXIST_MESSAGE, transactionNotExistExceptionToGetById.getMessage());
        verify(transactionRepository, times(3)).findById(3L);
    }

    @Test
    public void shouldThrowStatusValidateExceptionForCanceledStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.CANCELED)));

        final UpdateStatusRequest updateStatusRequest = getUpdateStatusRequest("SUCCESS");

        final StatusValidateException statusValidateExceptionSuccess = assertThrows(StatusValidateException.class, () -> {
            transactionService.updateTransactionStatusById(1L, updateStatusRequest);
        });

        assertEquals(INVALID_STATUS_CANCELED_CHANGE_MESSAGE, statusValidateExceptionSuccess.getMessage());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowStatusValidateExceptionForSuccessStatus() {
        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(getTransactionEntity(StatusEnum.SUCCESS)));

        final UpdateStatusRequest updateStatusRequest = getUpdateStatusRequest("PENDING");

        final StatusValidateException statusValidateExceptionSuccess = assertThrows(StatusValidateException.class, () -> {
            transactionService.updateTransactionStatusById(1L, updateStatusRequest);
        });

        assertEquals(INVALID_STATUS_SUCCESS_CHANGE_MESSAGE, statusValidateExceptionSuccess.getMessage());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowInvalidPageException() {
        final TransactionSearch transactionSearch = getTransactionSearch();

        final InvalidPageException invalidPageException = assertThrows(InvalidPageException.class, () -> {
            transactionService.getTransactionList(-1, transactionSearch);
        });

        assertEquals(INVALID_PAGE_MESSAGE, invalidPageException.getMessage());
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
        final UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();

        updateStatusRequest.setStatus(status);

        return updateStatusRequest;
    }

    private TransactionSearch getTransactionSearch() {
        return new TransactionSearch("APP", "PIX");
    }

    private Page<TransactionEntity> getTransactionPage() {
        final Pageable pageable = PageRequest.of(0, 10);
        final List<TransactionEntity> transactionEntityList = Collections.singletonList(getTransactionEntity(StatusEnum.SUCCESS));
        return new PageImpl<TransactionEntity>(transactionEntityList, pageable, 1);
    }

}