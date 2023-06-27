package com.dirt.api.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
public class CaptureMethodDto {

    @JsonProperty("id")
    @NotBlank(message = "Invalid captureMethodId")
    private String captureMethodId;

    @Range(min = 1, max = 3, message = "Invalid captureMethodtype [Value should be between 1-3]")
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
