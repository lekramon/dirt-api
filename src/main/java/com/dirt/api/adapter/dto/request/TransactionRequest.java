package com.dirt.api.adapter.dto.request;

import com.dirt.api.adapter.dto.CaptureMethodDto;
import com.dirt.api.adapter.dto.OtherAccountDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class TransactionRequest {

    @NotBlank(message = "Invalid ip, cannot be blank")
    @Size(max = 15, message = "The ip size must have a maximum of 15 characters")
    private String ip;

    @NotNull(message = "Invalid amount, cannot be null")
    private BigDecimal amount;

    @NotNull(message = "Invalid tax, cannot be null")
    private BigDecimal tax;

    @NotNull(message = "Invalid account, cannot be null")
    private Long accountId;

    @NotBlank(message = "Invalid description, cannot be blank")
    @Size(max = 100, message = "The description size must have a maximum of 100 characters")
    private String description;

    @Valid
    private CaptureMethodDto captureMethod;

    @NotBlank(message = "Invalid transactionType, cannot be blank")
    private String transactionType;

    @NotBlank(message = "Invalid operation, cannot be blank")
    private String operation;

    @Valid
    private OtherAccountDto otherAccount;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
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