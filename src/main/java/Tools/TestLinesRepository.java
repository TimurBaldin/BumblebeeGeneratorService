package Tools;
import Columns.ColumnLines;
import Rules.DAO.BaseRepository;
import Tables.StringTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
public class TestLinesRepository implements BaseRepository<ColumnLines,String> {
    private final String REPORT = Querys.KEY.getGET_TEST_DATA();
    private ArrayList<String> values_test = new ArrayList<String>();
    private Session session;
    private String COLUMN_NAME;
    private SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    public void create(List<String> values,String COLUMN_NAME) {
        this.values_test.addAll(values);
        this.COLUMN_NAME = COLUMN_NAME;

        for (int i = 0; i <= values_test.size ( ) - 1; i++) {
            session = sessionFactory.openSession ( );
            try {
                Transaction transaction = session.beginTransaction ( );
                StringTableBufer bufer = new StringTableBufer ( );
                bufer.setValue (values_test.get (i));
                bufer.setColumnName (COLUMN_NAME);
                session.save (bufer);
                transaction.commit ( );
                }catch (Exception ex){
                ex.printStackTrace();
                session.getTransaction ().rollback ();
            }finally {
                session.close ( );

            }
        }
        values_test.clear();
        }
    public List<ColumnLines> get(List<ColumnLines> columns) {
        List<ColumnLines> columnLines=new ArrayList<ColumnLines>();
        for (ColumnLines bufer:columns) {
            session = sessionFactory.openSession ( );
            Query query = session.createQuery(REPORT);
            query.setParameter("val1",bufer.getCOLUMN());
            query.setParameter("val2",0);
            bufer.setValues(query.list());
            columnLines.add(bufer);
            session.close();
        }

        return columnLines;

    }
}