package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.response.ErrorResponse;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirtExceptionHandlerTest {

    private final DirtExceptionHandler dirtExceptionHandler = new DirtExceptionHandler();

    @Test
    public void shouldHandleTransactionRequestEnumNotExistException() {

        EnumNotExistException exception = new EnumNotExistException("message");

        ResponseEntity<ErrorResponse> expectedResponseEntity = getErrorResponseEnum(exception);
        ResponseEntity<ErrorResponse> actualResponseEntity = dirtExceptionHandler.handleBadRequestException(exception);

        Assertions.assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertThat(actualResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedResponseEntity.getBody());
    }

    @Test
    public void shouldHandleAccountNotExistException() {

        AccountNotExistException exception = new AccountNotExistException("message");

        ResponseEntity<ErrorResponse> expectedResponseEntity = getErrorResponseAccount(exception);
        ResponseEntity<ErrorResponse> actualResponseEntity = dirtExceptionHandler.handleBadRequestException(exception);

        Assertions.assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertThat(actualResponseEntity.getBody()).usingRecursiveComparison().isEqualTo(expectedResponseEntity.getBody());

    }

    private ResponseEntity<ErrorResponse> getErrorResponseEnum(EnumNotExistException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    private ResponseEntity<ErrorResponse> getErrorResponseAccount(AccountNotExistException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }
}