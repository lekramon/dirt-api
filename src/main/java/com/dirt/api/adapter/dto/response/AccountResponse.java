package com.dirt.api.adapter.dto.response;

public class AccountResponse {
    private Long accountId;
    private String document;
    private String accountName;
    private String accountNum;
    private String accountNumAgency;
    private String accountNumBank;
    private char accountType;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountNumAgency() {
        return accountNumAgency;
    }

    public void setAccountNumAgency(String accountNumAgency) {
        this.accountNumAgency = accountNumAgency;
    }

    public String getAccountNumBank() {
        return accountNumBank;
    }

    public void setAccountNumBank(String accountNumBank) {
        this.accountNumBank = accountNumBank;
    }

    public char getAccountType() {
        return accountType;
    }

    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }
}
