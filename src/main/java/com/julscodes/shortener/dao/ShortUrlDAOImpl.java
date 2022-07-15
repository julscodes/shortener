package com.julscodes.shortener.dao;

import com.julscodes.shortener.Logger;
import com.julscodes.shortener.dao.entity.ShortUrl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.ManagedBean;
import javax.ws.rs.BadRequestException;
import java.time.Instant;
import java.util.List;

@ManagedBean
public class ShortUrlDAOImpl implements ShortUrlDAO {

    @Override
    public List<ShortUrl> listUrls() {
        Session session = SessionUtil.getSession();
        org.hibernate.query.Query query = session.createQuery("from ShortUrl");
        List<ShortUrl> urls = query.list();
        session.close();
        return urls;
    }

    @Override
    public ShortUrl getUrl(String id) {

        Session session = SessionUtil.getSession();
        String hql = "from ShortUrl where id = :id";
        org.hibernate.query.Query query = session.createQuery(hql);
        query.setParameter("id", id);
        ShortUrl url = (ShortUrl) query.uniqueResult();
        session.close();
        return url;
    }

    @Override
    public ShortUrl saveUrl(ShortUrl bean) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        ShortUrl shortUrl = saveUrl(session, bean);
        tx.commit();
        session.close();

        return shortUrl;

    }

    private ShortUrl saveUrl(Session session, ShortUrl bean) {
        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setId(bean.getId());
        shortUrl.setLongUrl(bean.getLongUrl());
        shortUrl.setDate(Instant.now());

        session.save(shortUrl);

        return shortUrl;

    }

    private ShortUrl updateUrl(Session session, ShortUrl bean) {
        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setId(bean.getId());
        shortUrl.setLongUrl(bean.getLongUrl());
        shortUrl.setDate(Instant.now());

        session.saveOrUpdate(shortUrl);

        return shortUrl;

    }

    @Override
    public int deleteUrl(String id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from ShortUrl where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        Logger.debug(getClass().getSimpleName(), "Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

    @Override
    public ShortUrl updateUrl(String id, ShortUrl shortUrl) {
        if (id == null || id.isEmpty())
            throw new BadRequestException("url id required!");
        shortUrl.setId(id);
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        ShortUrl shortUrlUpdated = updateUrl(session, shortUrl);
        tx.commit();
        session.close();
        return shortUrlUpdated;

    }
}
