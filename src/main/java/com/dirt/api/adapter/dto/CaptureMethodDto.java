package com.dirt.api.adapter.dto;

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

    @NotBlank(message = "Invalid captureMethodType, cannot be blank")
    private String type;

    public String getCaptureMethodId() {
        return captureMethodId;
    }

    public void setCaptureMethodId(String captureMethodId) {
        this.captureMethodId = captureMethodId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
