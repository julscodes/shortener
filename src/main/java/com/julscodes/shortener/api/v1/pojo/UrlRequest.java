package com.julscodes.shortener.api.v1.pojo;

public class UrlRequest {

    private String url;

    public UrlRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
