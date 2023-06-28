package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum CaptureMethodEnum {

    WEB(1), APP(2), ATM(3);

    private final int captureMethodTypeCode;

    CaptureMethodEnum(int captureMethodTypeCode) {
        this.captureMethodTypeCode = captureMethodTypeCode;
    }

    public static CaptureMethodEnum fromCode(int captureMethod) {
        for (CaptureMethodEnum captureMethodEnum : CaptureMethodEnum.values()) {
            if (captureMethodEnum.getCaptureMethodTypeCode() == captureMethod) {
                return captureMethodEnum;
            }
        }
        throw new EnumNotExistException("The captureMethodType: " + captureMethod + " doesn't exist");
    }

    public static CaptureMethodEnum fromValue(String captureMethod) {
        for (CaptureMethodEnum captureMethodEnum : CaptureMethodEnum.values()) {
            if (captureMethodEnum.name().equalsIgnoreCase(captureMethod)) {
                return captureMethodEnum;
            }
        }
        throw new EnumNotExistException("The captureMethodType: " + captureMethod + " doesn't exist");
    }

    public int getCaptureMethodTypeCode() {
        return captureMethodTypeCode;
    }
}
