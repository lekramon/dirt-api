package com.dirt.api.adapter.controller;

import com.dirt.api.adapter.dto.response.ErrorResponse;
import com.dirt.api.domain.exception.EnumNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DirtExceptionHandler {

    @ExceptionHandler(EnumNotExistException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException exception) {

        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }
}
