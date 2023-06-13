package com.dirt.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CAPTURE_METHOD_TYPE")
public class CaptureMethod {

    @Id
    @Column(name = "COD_CAPTURE_METHOD_TYPE")
    private int captureMethodCod;

    @Column(name = "DES_CAPTURE_METHOD_TYPE")
    private String captureMethodDes;

    public int getCaptureMethodCod() {
        return captureMethodCod;
    }

    public void setCaptureMethodCod(int captureMethodCod) {
        this.captureMethodCod = captureMethodCod;
    }

    public String getCaptureMethodDes() {
        return captureMethodDes;
    }

    public void setCaptureMethodDes(String captureMethodDes) {
        this.captureMethodDes = captureMethodDes;
    }
}
