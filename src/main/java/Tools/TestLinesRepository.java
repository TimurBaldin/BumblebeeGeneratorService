package Tools;

import Tables.StringTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TestLinesRepository {
    private final String NEXT_ID = "SELECT max(id) from Tables.StringTableBufer";
    private ArrayList<String> values_test = new ArrayList<String>();
    private String COLUMN_NAME;
    private SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();

    public TestLinesRepository(List<String> values, String COLUMN_NAME) {
        this.values_test.addAll(values);
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public void create() {
        for (int i = 0; i <= values_test.size() - 1; i++) {
            Session session = sessionFactory.openSession();
            try {
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
    }

    public List<StringTableBufer> get(String ColumnName) {
        /*
        This is prototipe
         */
        return null;

    }
}