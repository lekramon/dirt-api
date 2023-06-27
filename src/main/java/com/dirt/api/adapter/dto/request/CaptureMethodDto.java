package com.dirt.api.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaptureMethodDto {

    @JsonProperty("id")
    @NotBlank(message = "Invalid captureMethodId")
    private String captureMethodId;
    @NotNull(message = "Invalid type")
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
