package com.dirt.api.domain.enums;

public enum StatusEnum {

    PENDING(1), CANCELED(2), SUCCESS(3);

    private final int codStatus;

    StatusEnum(int codStatus) {
        this.codStatus = codStatus;
    }

    public int getCodStatus() {
        return codStatus;
    }
}
