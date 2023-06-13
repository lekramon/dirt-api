package com.dirt.api.domain.enums;

public enum TransactionTypeEnum {

    PIX(1),
    TED(2),
    DOC(3);

    private final int transactionTypeCod;

    TransactionTypeEnum(int transactionTypeCod) {
        this.transactionTypeCod = transactionTypeCod;
    }

    public int getTransactionTypeCod() {
        return transactionTypeCod;
    }
}
