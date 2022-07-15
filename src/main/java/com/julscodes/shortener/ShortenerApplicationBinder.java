package com.julscodes.shortener;

import com.julscodes.shortener.dao.ShortUrlDAO;
import com.julscodes.shortener.dao.ShortUrlDAOImpl;
import com.julscodes.shortener.service.UrlService;
import com.julscodes.shortener.service.UrlServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class ShortenerApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(ShortUrlDAOImpl.class).to(ShortUrlDAO.class).in(Singleton.class);
        bind(UrlServiceImpl.class).to(UrlService.class).in(Singleton.class);
    }
}
