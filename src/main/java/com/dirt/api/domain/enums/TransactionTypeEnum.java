package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum TransactionTypeEnum {

    PIX(1), TED(2), DOC(3);

    private final int transactionTypeCod;

    TransactionTypeEnum(int transactionTypeCod) {
        this.transactionTypeCod = transactionTypeCod;
    }

    public static TransactionTypeEnum fromCode(int transactionTypeCod) {
        for (TransactionTypeEnum type : TransactionTypeEnum.values()) {
            if (type.getTransactionTypeCod() == transactionTypeCod) {
                return type;
            }
        }
        throw new EnumNotExistException("The transactionType: " + transactionTypeCod + " doesn't exist");
    }

    public int getTransactionTypeCod() {
        return transactionTypeCod;
    }
}
