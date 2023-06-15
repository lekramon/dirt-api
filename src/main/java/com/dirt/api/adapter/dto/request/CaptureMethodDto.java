package com.dirt.api.adapter.dto.request;

import com.dirt.api.domain.enums.CaptureMethodEnum;

public class CaptureMethodDto {

    private String id;
    private CaptureMethodEnum type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CaptureMethodEnum getType() {
        return type;
    }

    public void setType(CaptureMethodEnum type) {
        this.type = type;
    }
}
