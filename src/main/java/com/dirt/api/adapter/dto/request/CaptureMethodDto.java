package com.dirt.api.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Component
public class CaptureMethodDto {

    @JsonProperty("id")
    @NotBlank(message = "Invalid captureMethodId, cannot be blank")
    @Size(max = 30, message = "The captureMethodId size must be have max 30 characters")
    private String captureMethodId;

    @Range(min = 1, max = 3, message = "Invalid captureMethodtype, value should be between [1-3]")
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
