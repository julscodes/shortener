package com.julscodes.shortener.api.v1.pojo;

import com.julscodes.shortener.dao.entity.ShortUrl;

import java.time.Instant;

public class UrlResponse extends BaseResponse {

    private ShortUrl shortUrl;

    public UrlResponse() {
    }

    public UrlResponse(ShortUrl shortUrl) {
        super(Instant.now().toEpochMilli());
        this.shortUrl = shortUrl;
    }

    public ShortUrl getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(ShortUrl shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "UrlResponse{" +
                "shortUrl=" + shortUrl +
                '}';
    }
}
