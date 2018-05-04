package Tools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUntil {
    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
