package com.dirt.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATION")
public class Operation {

    @Id
    @Column(name = "COD_OPERATION")
    private int operationCod;

    @Column(name = "DES_OPERATION")
    private String operationDes;

    public int getOperationCod() {
        return operationCod;
    }

    public void setOperationCod(int operationCod) {
        this.operationCod = operationCod;
    }

    public String getOperationDes() {
        return operationDes;
    }

    public void setOperationDes(String operationDes) {
        this.operationDes = operationDes;
    }
}
