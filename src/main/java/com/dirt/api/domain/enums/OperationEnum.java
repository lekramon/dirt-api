package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum OperationEnum {

    CREDIT(1), DEBIT(2);

    private final int operationCode;

    OperationEnum(int operationCode) {
        this.operationCode = operationCode;
    }

    public static OperationEnum fromCode(int operation) {
        for (OperationEnum operationEnum : OperationEnum.values()) {
            if (operationEnum.getOperationCode() == operation) {
                return operationEnum;
            }
        }
        throw new EnumNotExistException("The operation: " + operation + " doesn't exist");
    }

    public int getOperationCode() {
        return operationCode;
    }
}
