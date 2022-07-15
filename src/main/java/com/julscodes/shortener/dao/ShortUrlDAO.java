package com.julscodes.shortener.dao;

import com.julscodes.shortener.dao.entity.ShortUrl;

import java.util.List;

public interface ShortUrlDAO {

    List<ShortUrl> listUrls();

    ShortUrl getUrl(String id);

    ShortUrl saveUrl(ShortUrl bean);

    int deleteUrl(String id);

    ShortUrl updateUrl(String id, ShortUrl shortUrl);
}
