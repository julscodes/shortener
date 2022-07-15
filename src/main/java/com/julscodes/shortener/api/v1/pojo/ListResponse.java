package com.julscodes.shortener.api.v1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.julscodes.shortener.dao.entity.ShortUrl;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse extends BaseResponse {

    private List<ShortUrl> items;

    public ListResponse() {
    }

    public ListResponse(List<ShortUrl> items) {
        super(Instant.now().toEpochMilli());
        this.items = items;
    }

    public List<ShortUrl> getItems() {
        return items;
    }

    public void setItems(List<ShortUrl> items) {
        this.items = items;
    }
}
