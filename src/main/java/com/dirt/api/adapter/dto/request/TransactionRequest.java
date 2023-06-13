package com.dirt.api.adapter.dto.request;

import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;

public class TransactionRequest {
    private String ip;
    private Double amount;
    private Double tax;
    private String accountId;
    private String description;
    private CaptureMethodRequest captureMethodRequest;
    private TransactionTypeEnum transactionType;
    private OperationEnum operation;
    private OtherAccountRequest otherAccountRequest;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CaptureMethodRequest getCaptureMethodRequest() {
        return captureMethodRequest;
    }

    public void setCaptureMethodRequest(CaptureMethodRequest captureMethodRequest) {
        this.captureMethodRequest = captureMethodRequest;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public OtherAccountRequest getOtherAccountRequest() {
        return otherAccountRequest;
    }

    public void setOtherAccountRequest(OtherAccountRequest otherAccountRequest) {
        this.otherAccountRequest = otherAccountRequest;
    }
}
