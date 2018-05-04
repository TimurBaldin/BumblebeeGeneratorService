package Tools;

import Tables.StringTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LineWriterTool {
    private final String NEXT_ID = "SELECT max(id) from Tables.StringTableBufer";
    private final String VALUES_FROM_DB = "from Tables.StringTableBufer where ColumnName=:ColumnName";
    private ArrayList<String> values_test = new ArrayList<String>();
//    private Session session;
    private String COLUMN_NAME;
//    private SessionFactory sessionFactory = SessionUntil.getInstance();
//
    public LineWriterTool(List<String> values, String COLUMN_NAME) {
        this.values_test.addAll(values);
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public void buildTable() {
        Session session = null;
        for (int i = 0; i <= values_test.size() - 1; i++) {
            try {
                session = SessionUntil.getInstance().openSession();
                Transaction transaction = session.beginTransaction();
                StringTableBufer bufer = new StringTableBufer();
                bufer.setValue(values_test.get(i));
                bufer.setColumnName(COLUMN_NAME);
                session.save(bufer);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
        SessionUntil.getInstance().close();

    }

    public List<StringTableBufer> returnTable(String ColumnName) {
        Session session = null;
        session = SessionUntil.getInstance().openSession();
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
        SessionUntil.getInstance().close();
        return table;

    }
}
