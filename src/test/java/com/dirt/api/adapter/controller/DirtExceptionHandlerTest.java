package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.response.ErrorResponse;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DirtExceptionHandlerTest {

    private static final String DEFAULT_MESSAGE = "Invalid tax, cannot be null";
    private static final String OBJECT_NAME = "TransactionRequest";
    private final DirtExceptionHandler dirtExceptionHandler = new DirtExceptionHandler();

    @Test
    public void shouldHandleTransactionRequestEnumNotExistException() {
        final EnumNotExistException exception = new EnumNotExistException("message");

        final ResponseEntity<ErrorResponse> expectedResponseEntity = getErrorResponseEnum(exception);
        final ResponseEntity<ErrorResponse> actualResponseEntity = dirtExceptionHandler.handleBadRequestException(exception);

        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertThat(actualResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedResponseEntity.getBody());
    }

    @Test
    public void shouldHandleAccountNotExistException() {
        final AccountNotExistException exception = new AccountNotExistException("message");

        final ResponseEntity<ErrorResponse> expectedResponseEntity = getErrorResponseAccount(exception);
        final ResponseEntity<ErrorResponse> actualResponseEntity = dirtExceptionHandler.handleBadRequestException(exception);

        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertThat(actualResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedResponseEntity.getBody());

    }

    @Test
    public void shouldHandleMethodArgumentNotValidException() {
        final MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);

        when(methodArgumentNotValidException.getAllErrors()).thenReturn(getObjectErrorList());

        final ResponseEntity<List<ErrorResponse>> expectedListResponseEntity = getErrorResponses(getObjectErrorList());
        final ResponseEntity<List<ErrorResponse>> actualListResponseEntity = dirtExceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException);

        assertThat(expectedListResponseEntity).usingRecursiveComparison().isEqualTo(actualListResponseEntity);
        assertEquals(expectedListResponseEntity.getStatusCode(), actualListResponseEntity.getStatusCode());
        assertThat(expectedListResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(actualListResponseEntity.getBody());
    }

    private ResponseEntity<ErrorResponse> getErrorResponseEnum(EnumNotExistException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    private ResponseEntity<ErrorResponse> getErrorResponseAccount(AccountNotExistException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    private ResponseEntity<List<ErrorResponse>> getErrorResponses(List<ObjectError> objectErrors) {
        final List<ErrorResponse> errorResponses = new ArrayList<>();
        for (ObjectError objectError : objectErrors) {
            errorResponses.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(errorResponses);
    }

    private List<ObjectError> getObjectErrorList() {
        final ObjectError objectError = new ObjectError(OBJECT_NAME, DEFAULT_MESSAGE);
        final List<ObjectError> objectErrorList = new ArrayList<>();
        objectErrorList.add(objectError);

        return objectErrorList;
    }

}