package com.dirt.api.adapter.dto.request;

public class TransactionRequest {
    private String ip;
    private Double amount;
    private Double tax;
    private Long accountId;
    private String description;
    private CaptureMethodDto captureMethodDto;
    private int transactionType;
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
