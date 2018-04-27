package Tools;
import Tables.StringTableBufer;
import org.hibernate.*;
import java.util.*;
public class LineWriterTool {
    private ArrayList<String> values_test=new ArrayList<String> ();
    private Session session;
    private String COLUMN_NAME;
    public  LineWriterTool(List<String> values,String COLUMN_NAME){
        this.values_test.addAll (values);
        this.COLUMN_NAME=COLUMN_NAME;
        }
        public void writeBD(){
            SessionFactory sessionFactory=SessionUntil.getInstance ();
try {

    for (int i = 0; i <= values_test.size ( ) - 1; i++) {
        session = sessionFactory.openSession ( );
        Transaction transaction = session.beginTransaction ( );
        StringTableBufer bufer = new StringTableBufer ( );
        bufer.setId (i);
        bufer.setValue (values_test.get (i));

        bufer.setColumnName (COLUMN_NAME);
        session.save (bufer);
        transaction.commit ( );
    }
    session.close ( );
    sessionFactory.close ( );
}catch (Exception e){ e.printStackTrace();}
        }
}
