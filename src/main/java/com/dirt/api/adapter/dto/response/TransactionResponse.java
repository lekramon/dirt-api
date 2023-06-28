package com.dirt.api.adapter.dto.response;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;

public class TransactionResponse {

    private Long transactionId;
    private String transactionIp;
    private Double transactionAmount;
    private Double transactionTax;
    private AccountResponse account;
    private String description;
    private String transactionType;
    private String operation;
    private CaptureMethodDto captureMethod;
    private OtherAccountDto otherAccount;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionIp() {
        return transactionIp;
    }

    public void setTransactionIp(String transactionIp) {
        this.transactionIp = transactionIp;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getTransactionTax() {
        return transactionTax;
    }

    public void setTransactionTax(Double transactionTax) {
        this.transactionTax = transactionTax;
    }

    public AccountResponse getAccount() {
        return account;
    }

    public void setAccount(AccountResponse account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public CaptureMethodDto getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(CaptureMethodDto captureMethod) {
        this.captureMethod = captureMethod;
    }

    public OtherAccountDto getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(OtherAccountDto otherAccount) {
        this.otherAccount = otherAccount;
    }
}
