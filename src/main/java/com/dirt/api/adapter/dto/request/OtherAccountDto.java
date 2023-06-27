package com.dirt.api.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class OtherAccountDto {

    @JsonProperty("number")
    @NotNull(message = "Invalid accountNumber")
    private String accountNumber;
    @JsonProperty("agency")
    @NotNull(message = "Invalid accountAgency")
    private String accountAgency;
    @JsonProperty("bankCode")
    @NotNull(message = "Invalid accountBankCode")
    private String accountBankCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountAgency() {
        return accountAgency;
    }

    public void setAccountAgency(String accountAgency) {
        this.accountAgency = accountAgency;
    }

    public String getAccountBankCode() {
        return accountBankCode;
    }

    public void setAccountBankCode(String accountBankCode) {
        this.accountBankCode = accountBankCode;
    }
}
