package com.dirt.api.domain.entity;

import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.enums.OperationEnum;
import com.dirt.api.domain.enums.StatusEnum;
import com.dirt.api.domain.enums.TransactionTypeEnum;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "COD_CAPTURE_METHOD_TYPE")
    private CaptureMethodEnum captureMethod;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "COD_TRANSACTION_TYPE")
    private TransactionTypeEnum transactionType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "COD_OPERATION")
    private OperationEnum operation;

    @Column(name = "DAT_TRANSACTION")
    private Timestamp transactionDat;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "COD_STATUS")
    private StatusEnum status;

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

    public CaptureMethodEnum getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(CaptureMethodEnum captureMethod) {
        this.captureMethod = captureMethod;
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

    public Timestamp getTransactionDat() {
        return transactionDat;
    }

    public void setTransactionDat(Timestamp transactionDat) {
        this.transactionDat = transactionDat;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
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
