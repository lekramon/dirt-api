package com.dirt.api.domain.exception;

public class EnumNotExistException extends RuntimeException {

    public EnumNotExistException(String message) {
        super(message);
    }
}
