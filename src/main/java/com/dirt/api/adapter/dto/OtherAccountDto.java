package com.dirt.api.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
public class OtherAccountDto {

    @JsonProperty("number")
    @NotBlank(message = "Invalid accountNumber, cannot be blank")
    @Size(max = 20, message = "The accountNumber size must have max 20 characters")
    private String accountNumber;
    @JsonProperty("agency")
    @NotBlank(message = "Invalid accountAgency, cannot be blank")
    @Size(max = 6, message = "The accountAgency size must have max 6 characters")
    private String accountAgency;
    @JsonProperty("bankCode")
    @NotBlank(message = "Invalid accountBankCode, cannot be blank")
    @Size(max = 4, message = "The accountBankCode size must have max 4 characters")
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
