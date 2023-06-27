package com.dirt.api.adapter.dto.request;

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
    private CaptureMethodDto captureMethodDto;
    @NotNull(message = "Invalid transactionType")
    private int transactionType;
    @NotNull(message = "Invalid operation")
    private int operation;
    private OtherAccountDto otherAccountDto;

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

    public CaptureMethodDto getCaptureMethodRequest() {
        return captureMethodDto;
    }

    public void setCaptureMethodRequest(CaptureMethodDto captureMethodDto) {
        this.captureMethodDto = captureMethodDto;
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

    public OtherAccountDto getOtherAccountRequest() {
        return otherAccountDto;
    }

    public void setOtherAccountRequest(OtherAccountDto otherAccountDto) {
        this.otherAccountDto = otherAccountDto;
    }
}
