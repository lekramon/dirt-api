package com.dirt.api.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaptureMethodDto {

    @JsonProperty("id")
    private String captureMethodId;
    private int type;

    public String getCaptureMethodId() {
        return captureMethodId;
    }

    public void setCaptureMethodId(String captureMethodId) {
        this.captureMethodId = captureMethodId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
