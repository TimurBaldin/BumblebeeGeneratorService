package Tables;

import Tools.SessionUntil;
import org.hibernate.*;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    String query ="VYBQ";
    String str="from StringTableBufer where value='VYBQ'";
    public void test(){
        SessionFactory sessionFactory=SessionUntil.getInstance ();
       Session session = sessionFactory.openSession ( );
      Query query= session.createQuery (str);
        List<StringTableBufer> res=query.getResultList ();
      System.out.println ("Result@  "+res.get (0).getColumnName ());

    }
}
