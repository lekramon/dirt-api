package com.dirt.api.adapter.dto.request;

public class TransactionSearch {

    private String captureMethodType;

    private String transactionType;

    public TransactionSearch(String captureMethodType, String transactionType) {
        this.captureMethodType = captureMethodType;
        this.transactionType = transactionType;
    }

    public String getCaptureMethodType() {
        return captureMethodType;
    }

    public void setCaptureMethodType(String captureMethodType) {
        this.captureMethodType = captureMethodType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
