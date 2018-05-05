package Tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum SessionUntil {
    INSTANCE;

    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
