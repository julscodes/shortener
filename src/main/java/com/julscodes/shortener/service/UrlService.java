package com.julscodes.shortener.service;

import com.julscodes.shortener.dao.entity.ShortUrl;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public interface UrlService {
    ShortUrl shortenUrl(String longUrl);

    ShortUrl getShortUrl(String id);

    ShortUrl updateUrl(String id, ShortUrl shortUrl);

    int deleteUrl(String id);

    List<ShortUrl> listUrls();

    String generateId();
}
