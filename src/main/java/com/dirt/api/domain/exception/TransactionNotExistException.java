package com.dirt.api.domain.exception;

public class TransactionNotExistException extends RuntimeException {

    public TransactionNotExistException(String message) {
        super(message);
    }
}
