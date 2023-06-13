package com.dirt.api.domain.enums;

public enum CaptureMethodEnum {

    WEB(1), APP(2), ATM(3);

    private final int captureMethodType;

    CaptureMethodEnum(int captureMethodType) {
        this.captureMethodType = captureMethodType;
    }

    public int getCaptureMethodType() {
        return captureMethodType;
    }
}
