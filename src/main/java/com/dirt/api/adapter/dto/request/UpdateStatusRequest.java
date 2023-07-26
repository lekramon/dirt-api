package com.dirt.api.adapter.dto.request;

import javax.validation.constraints.NotBlank;

public class UpdateStatusRequest {
    @NotBlank(message = "Invalid status, cannot be blank")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
