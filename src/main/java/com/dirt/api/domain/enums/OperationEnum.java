package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum OperationEnum {

    CREDIT(1), DEBIT(2);

    private final int operation;

    OperationEnum(int operation) {
        this.operation = operation;
    }

    public static OperationEnum fromCode(int operation) {
        for (OperationEnum operationEnum : OperationEnum.values()) {
            if (operationEnum.getOperation() == operation) {
                return operationEnum;
            }
        }
        throw new EnumNotExistException("The operation: " + operation + " doesn't exist");
    }

    public int getOperation() {
        return operation;
    }
}
