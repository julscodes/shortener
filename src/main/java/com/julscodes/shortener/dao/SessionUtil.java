package com.julscodes.shortener.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.TimeZone;

public class SessionUtil {

    private static final SessionUtil instance = new SessionUtil();
    private final SessionFactory sessionFactory;

    public static SessionUtil getInstance() {
        return instance;
    }

    private SessionUtil() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        Session session = getInstance().sessionFactory.withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC"))
                .openSession();

        return session;
    }

    public static void shutDown() {
        getInstance().sessionFactory.close();
    }

}
