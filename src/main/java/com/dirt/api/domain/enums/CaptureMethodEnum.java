package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum CaptureMethodEnum {

    WEB(1), APP(2), ATM(3);

    private final int captureMethodType;

    CaptureMethodEnum(int captureMethodType) {
        this.captureMethodType = captureMethodType;
    }

    public static CaptureMethodEnum fromCode(int captureMethod) {
        for (CaptureMethodEnum captureMethodEnum : CaptureMethodEnum.values()) {
            if (captureMethodEnum.getCaptureMethodType() == captureMethod) {
                return captureMethodEnum;
            }
        }
        throw new EnumNotExistException("The captureMethodType: " + captureMethod + " doesn't exist");
    }

    public int getCaptureMethodType() {
        return captureMethodType;
    }
}
