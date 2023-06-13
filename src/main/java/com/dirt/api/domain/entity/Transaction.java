package com.dirt.api.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_TRANSACTION")
    private Long transactionId;

    @Column(name = "NUM_IP")
    private String transactionIp;

    @Column(name = "NUM_AMOUNT")
    private double transactionAmount;

    @Column(name = "NUM_TAX")
    private double transactionTax;

    @Column(name = "IDT_ACCOUNT")
    private long transactionAccount;

    @Column(name = "DES_TRANSACTION")
    private String transactionDes;

    @Column(name = "IDT_CAPTURE_METHOD")
    private String transactionCaptureMethod;

    @ManyToOne
    @JoinColumn(name = "COD_CAPTURE_METHOD_TYPE")
    private CaptureMethod captureMethod;

    @ManyToOne
    @JoinColumn(name = "COD_TRANSACTION_TYPE")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "COD_OPERATION")
    private Operation operation;

    @Column(name = "DAT_TRANSACTION")
    private Timestamp transactionDat;

    @ManyToOne
    @JoinColumn(name = "COD_STATUS")
    private Status status;

    @Column(name = "NUM_OTHER_ACCOUNT")
    private String transactionOtherAccount;

    @Column(name = "NUM_OTHER_ACCOUNT_AGENCY")
    private String transactionOtherAccountAgency;

    @Column(name = "NUM_OTHER_ACCOUNT_BANK")
    private String transactionOtherAccountBank;

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

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getTransactionTax() {
        return transactionTax;
    }

    public void setTransactionTax(double transactionTax) {
        this.transactionTax = transactionTax;
    }

    public long getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(long transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    public String getTransactionDes() {
        return transactionDes;
    }

    public void setTransactionDes(String transactionDes) {
        this.transactionDes = transactionDes;
    }

    public String getTransactionCaptureMethod() {
        return transactionCaptureMethod;
    }

    public void setTransactionCaptureMethod(String transactionCaptureMethod) {
        this.transactionCaptureMethod = transactionCaptureMethod;
    }

    public CaptureMethod getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(CaptureMethod captureMethod) {
        this.captureMethod = captureMethod;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Timestamp getTransactionDat() {
        return transactionDat;
    }

    public void setTransactionDat(Timestamp transactionDat) {
        this.transactionDat = transactionDat;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTransactionOtherAccount() {
        return transactionOtherAccount;
    }

    public void setTransactionOtherAccount(String transactionOtherAccount) {
        this.transactionOtherAccount = transactionOtherAccount;
    }

    public String getTransactionOtherAccountAgency() {
        return transactionOtherAccountAgency;
    }

    public void setTransactionOtherAccountAgency(String transactionOtherAccountAgency) {
        this.transactionOtherAccountAgency = transactionOtherAccountAgency;
    }

    public String getTransactionOtherAccountBank() {
        return transactionOtherAccountBank;
    }

    public void setTransactionOtherAccountBank(String transactionOtherAccountBank) {
        this.transactionOtherAccountBank = transactionOtherAccountBank;
    }
}
