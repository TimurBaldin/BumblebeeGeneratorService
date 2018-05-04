package Tools;

import Tables.StringTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
<<<<<<< HEAD:src/main/java/Tools/TestLinesRepository.java
import java.util.*;
public class TestLinesRepository {
    private ArrayList<String> values_test=new ArrayList<String> ();
    private Session session;
    private String COLUMN_NAME;
    private   volatile Integer row_id;
    private final String NEXT_ID="SELECT max(id) from Tables.StringTableBufer";
    private final String VALUES_FROM_DB="from Tables.StringTableBufer where ColumnName=:ColumnName";
    private  SessionFactory sessionFactory=SessionUntil.getInstance ();
    public TestLinesRepository(List<String> values, String COLUMN_NAME){
       this.values_test.addAll (values);
        this.COLUMN_NAME=COLUMN_NAME;
       }
        public void create(){
        session = sessionFactory.openSession ( );
        try {
            Query query=session.createQuery (NEXT_ID);
                row_id=(Integer) query.getResultList ().get (0);
                if(row_id==null){
                     row_id=1;
                 }else {
                    ++row_id;
                }
                }catch (Exception e){
         session.getTransaction ().rollback ();
=======
import java.util.ArrayList;
import java.util.List;

public class LineWriterTool {
    private final String NEXT_ID = "SELECT max(id) from Tables.StringTableBufer";
    private final String VALUES_FROM_DB = "from Tables.StringTableBufer where ColumnName=:ColumnName";
    private ArrayList<String> values_test = new ArrayList<String>();
    private Session session;
    private String COLUMN_NAME;
    private volatile Integer row_id;
    private SessionFactory sessionFactory = SessionUntil.getInstance();

    public LineWriterTool(List<String> values, String COLUMN_NAME) {
        this.values_test.addAll(values);
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public void buildTable() {
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery(NEXT_ID);
            row_id = (Integer) query.getResultList().get(0);
            if (row_id == null) {
                row_id = 1;
            } else {
                ++row_id;
            }
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
>>>>>>> 8c2057f5290e03dd3abc1132acb8c965c3ed7b26:src/main/java/Tools/LineWriterTool.java
            e.printStackTrace();
        }finally {
            session.close ();
        }
<<<<<<< HEAD:src/main/java/Tools/TestLinesRepository.java
        for (int i = 0; i <= values_test.size ( ) - 1; i++) {
            session = sessionFactory.openSession ( );
            try {
                Transaction transaction = session.beginTransaction ( );
            StringTableBufer bufer = new StringTableBufer ( );
            bufer.setId (row_id);
            bufer.setValue (values_test.get (i));
            bufer.setColumnName (COLUMN_NAME);
            session.save (bufer);
            transaction.commit ( );
            row_id++;
        }catch (Exception ex){
            ex.printStackTrace();
          session.getTransaction ().rollback ();
        }finally {
            session.close ( );
=======
        for (int i = 0; i <= values_test.size() - 1; i++) {
            try {
                session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                StringTableBufer bufer = new StringTableBufer();
                bufer.setId(row_id);
                bufer.setValue(values_test.get(i));
                bufer.setColumnName(COLUMN_NAME);
                session.save(bufer);
                transaction.commit();
                row_id++;
            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
>>>>>>> 8c2057f5290e03dd3abc1132acb8c965c3ed7b26:src/main/java/Tools/LineWriterTool.java
        }
        sessionFactory.close();

    }

<<<<<<< HEAD:src/main/java/Tools/TestLinesRepository.java
       }
       public  List<StringTableBufer> returnTable(String ColumnName){
       /* session = sessionFactory.openSession ( );
           List<StringTableBufer> table=null;
           try {
               Query query=session.createQuery (VALUES_FROM_DB);
               query.setParameter ("ColumnName",ColumnName);
               table=query.getResultList ();
               session.close ();
               }catch (Exception e){
               e.printStackTrace();
               session.getTransaction ().rollback ();
           }
           sessionFactory.close ( );
           */
           return null;
=======
    public List<StringTableBufer> returnTable(String ColumnName) {
        session = sessionFactory.openSession();
        List<StringTableBufer> table = null;
        try {
            Query query = session.createQuery(VALUES_FROM_DB);
            query.setParameter("ColumnName", ColumnName);
            table = query.getResultList();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        sessionFactory.close();
        return table;
>>>>>>> 8c2057f5290e03dd3abc1132acb8c965c3ed7b26:src/main/java/Tools/LineWriterTool.java

    }
}
