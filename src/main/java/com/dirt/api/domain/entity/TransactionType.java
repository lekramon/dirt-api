package com.dirt.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_TYPE")
public class TransactionType {

    @Id
    @Column(name = "COD_TRANSACTION_TYPE")
    private int transactionTypeCod;

    @Column(name = "DES_TRANSACTION_TYPE")
    private String transactionTypeDes;

    public int getTransactionTypeCod() {
        return transactionTypeCod;
    }

    public void setTransactionTypeCod(int transactionTypeCod) {
        this.transactionTypeCod = transactionTypeCod;
    }

    public String getTransactionTypeDes() {
        return transactionTypeDes;
    }

    public void setTransactionTypeDes(String transactionTypeDes) {
        this.transactionTypeDes = transactionTypeDes;
    }
}
