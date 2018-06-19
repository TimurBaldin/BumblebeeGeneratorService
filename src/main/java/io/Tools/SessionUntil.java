package io.Tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum SessionUntil {
    INSTANCE;
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public SessionFactory getInstance() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
