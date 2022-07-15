package com.julscodes.shortener.api.v1.pojo;

public class BaseResponse {

    private Long timestamp;

    public BaseResponse() {
    }

    public BaseResponse(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
