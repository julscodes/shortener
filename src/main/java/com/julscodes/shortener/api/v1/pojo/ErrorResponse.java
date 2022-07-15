package com.julscodes.shortener.api.v1.pojo;

import java.time.Instant;

public class ErrorResponse extends BaseResponse {

    private String reason;

    public ErrorResponse() {
    }

    public ErrorResponse(String reason) {
        super(Instant.now().toEpochMilli());
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
