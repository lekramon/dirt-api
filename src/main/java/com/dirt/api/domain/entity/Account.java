package com.dirt.api.domain.entity;


import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_ACCOUNT")
    private Long accountId;

    @Column(name = "NAME_ACCOUNT")
    private String accountName;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "NUM_ACCOUNT")
    private String accountNum;

    @Column(name = "NUM_ACCOUNT_AGENCY")
    private String accountNumAgency;

    @Column(name = "NUM_ACCOUNT_BANK")
    private String accountNumBank;

    @Column(name = "COD_ACCOUNT_TYPE")
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
