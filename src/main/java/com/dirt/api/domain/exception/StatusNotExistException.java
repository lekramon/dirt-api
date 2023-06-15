package com.dirt.api.domain.exception;

public class StatusNotExistException extends RuntimeException {

    public StatusNotExistException(String message) {
        super(message);
    }
}
