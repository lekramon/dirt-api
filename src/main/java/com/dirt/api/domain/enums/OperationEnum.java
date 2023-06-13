package com.dirt.api.domain.enums;

public enum OperationEnum {

    CREDIT(1), DEBIT(2);

    private final int operation;

    OperationEnum(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }
}
