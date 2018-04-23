package TipDataRule;
import Rules.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.lang.Throwable;
import org.hibernate.*;

public class LogicRule {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory=new Configuration ().buildSessionFactory ();
            Session session=sessionFactory.openSession ();
            session.close ();
            sessionFactory.close ();
        } catch (Exception e) {
            e.printStackTrace ( );
        }



    }
}
