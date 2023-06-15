package com.dirt.api.adapter.dto.request;

import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;

public class TransactionRequest {
    private String ip;
    private Double amount;
    private Double tax;
    private Long accountId;
    private String description;
    private CaptureMethodDto captureMethodDto;
    private TransactionTypeEnum transactionType;
    private OperationEnum operation;
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

    public OtherAccountDto getOtherAccountRequest() {
        return otherAccountDto;
    }

    public void setOtherAccountRequest(OtherAccountDto otherAccountDto) {
        this.otherAccountDto = otherAccountDto;
    }
}
