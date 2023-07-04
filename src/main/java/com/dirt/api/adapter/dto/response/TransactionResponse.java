package com.dirt.api.adapter.dto.response;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;

import java.math.BigDecimal;

public class TransactionResponse {

    private Long transactionId;
    private String status;
    private String transactionIp;
    private BigDecimal transactionAmount;
    private BigDecimal transactionTax;
    private String description;
    private String transactionType;
    private AccountResponse account;
    private CaptureMethodDto captureMethod;
    private String operation;
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

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransactionTax() {
        return transactionTax;
    }

    public void setTransactionTax(BigDecimal transactionTax) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}