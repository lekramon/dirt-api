package com.dirt.api.adapter.dto.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransactionRequest {

    @NotBlank(message = "Invalid ip")
    private String ip;
    @NotNull(message = "Invalid amount")
    private Double amount;
    @NotNull(message = "Invalid tax")
    private Double tax;
    @NotNull(message = "Invalid account")
    private Long accountId;
    @NotBlank(message = "Invalid description")
    private String description;
    @Valid
    private CaptureMethodDto captureMethod;
    @Range(min = 1, max = 3, message = "Invalid transactionType [Value should be between 1-3]")
    private int transactionType;
    @Range(min = 1, max = 3, message = "Invalid operation [Value should be between 1-2]")
    private int operation;
    @Valid
    private OtherAccountDto otherAccount;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
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
