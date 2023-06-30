package com.dirt.api.domain.enums;

import com.dirt.api.domain.exception.EnumNotExistException;

public enum StatusEnum {

    PENDING(1), CANCELED(2), SUCCESS(3);

    private final int codStatus;

    StatusEnum(int codStatus) {
        this.codStatus = codStatus;
    }

    public static StatusEnum fromCode(int codStatus) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getCodStatus() == codStatus) {
                return status;
            }
        }
        throw new EnumNotExistException("The status: " + codStatus + " doesn't exist");
    }

    public static StatusEnum fromValue(String status) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.name().equalsIgnoreCase(status)) {
                return statusEnum;
            }
        }
        throw new EnumNotExistException("The status: " + status + " doesn't exist");
    }

    public int getCodStatus() {
        return codStatus;
    }
}
