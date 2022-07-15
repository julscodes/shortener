package com.julscodes.shortener;

import com.julscodes.shortener.dao.SessionUtil;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Logger.info(getClass().getSimpleName(), " + starting");
        Session session = SessionUtil.getSession();
        session.close();
        Logger.info(getClass().getSimpleName(), " - started");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Logger.info(getClass().getSimpleName(), " + shutting down");

        SessionUtil.shutDown();

        Logger.info(getClass().getSimpleName(), " - shutting down");
    }
}
