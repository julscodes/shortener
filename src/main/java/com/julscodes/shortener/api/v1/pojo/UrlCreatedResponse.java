package com.julscodes.shortener.api.v1.pojo;

import com.julscodes.shortener.dao.entity.ShortUrl;

import java.time.Instant;

public class UrlCreatedResponse extends BaseResponse {

    private ShortUrl data;

    private String shortUrl;

    public UrlCreatedResponse() {
    }

    public UrlCreatedResponse(ShortUrl data, String shortUrl) {
        super(Instant.now().toEpochMilli());
        this.data = data;
        this.shortUrl = shortUrl;
    }

    public ShortUrl getData() {
        return data;
    }

    public void setData(ShortUrl data) {
        this.data = data;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "UrlCreatedResponse{" +
                "data=" + data +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
