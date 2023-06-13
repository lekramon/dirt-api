package com.dirt.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public class Status {

    @Id
    @Column(name = "COD_STATUS")
    private int statusCod;

    @Column(name = "DES_STATUS")
    private String statusDes;

    public int getStatusCod() {
        return statusCod;
    }

    public void setStatusCod(int statusCod) {
        this.statusCod = statusCod;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
}
