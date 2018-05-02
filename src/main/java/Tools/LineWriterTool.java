package Tools;
import Tables.StringTableBufer;
import org.hibernate.*;
import org.hibernate.query.*;
import javax.persistence.Query;
import java.util.*;
public class LineWriterTool {
    private ArrayList<String> values_test=new ArrayList<String> ();
    private Session session;
    private String COLUMN_NAME;
    private   volatile Integer row_id;
    private final String NEXT_ID="SELECT max(id) from Tables.StringTableBufer";
    private final String VALUES_FROM_DB="from Tables.StringTableBufer where ColumnName=:ColumnName";
    private  SessionFactory sessionFactory=SessionUntil.getInstance ();
    public  LineWriterTool(List<String> values,String COLUMN_NAME){
       this.values_test.addAll (values);
        this.COLUMN_NAME=COLUMN_NAME;
       }
        public void buildTable(){
        try {
                session = sessionFactory.openSession ( );
                Query query=session.createQuery (NEXT_ID);
                row_id=(Integer) query.getResultList ().get (0);
                if(row_id==null){
                     row_id=1;
                 }else {
                    ++row_id;
                }
                session.close ();
        }catch (Exception e){
            session.getTransaction ().rollback ();
            e.printStackTrace();
        }
                for (int i = 0; i <= values_test.size ( ) - 1; i++) {
        try {
            session = sessionFactory.openSession ( );
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
        }
        }
        sessionFactory.close ( );

       }
       public  List<StringTableBufer> returnTable(String ColumnName){
        session = sessionFactory.openSession ( );
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
           return table;

       }
}
