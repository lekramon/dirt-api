package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.response.ErrorResponse;
import com.dirt.api.domain.exception.AccountNotExistException;
import com.dirt.api.domain.exception.EnumNotExistException;
import com.dirt.api.domain.exception.StatusValidateException;
import com.dirt.api.domain.exception.TransactionNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class DirtExceptionHandler {

    @ExceptionHandler({EnumNotExistException.class, AccountNotExistException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        final List<ErrorResponse> errorResponsesList = new ArrayList<>();

        for (ObjectError objectError : exception.getAllErrors()) {
            errorResponsesList.add(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(errorResponsesList);
    }

    @ExceptionHandler(TransactionNotExistException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(StatusValidateException.class)
    public ResponseEntity<ErrorResponse> handleNotAcceptableException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage()));
    }
}
