package com.rufus.bumblebee.Main.Tools;
import com.rufus.bumblebee.Main.Columns.ColumnNum;
import com.rufus.bumblebee.Main.Rules.DAO.BaseRepository;
import com.rufus.bumblebee.Main.Tables.NumberTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TestNumberRepository implements BaseRepository<ColumnNum,Number> {
    private ArrayList<Number> values_test = new ArrayList<Number>();
    private String COLUMN_NAME;
    private final String REPORT = Querys.KEY.getGET_TEST_DATA();
    private Session session;
    private SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    @Override
    public void create(List<Number> values, String COLUMN_NAME) {
        this.values_test.addAll(values);
        this.COLUMN_NAME = COLUMN_NAME;
        for (int i = 0; i <= values_test.size() - 1; i++) {
            session = sessionFactory.openSession();
            try {
                Transaction transaction = session.beginTransaction();
                NumberTableBufer bufer = new NumberTableBufer();
                bufer.setValuetest(values_test.get(i).toString());
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

    @Override
    public List<ColumnNum> get(List<ColumnNum> columns) {
            List<ColumnNum> columnNums=new ArrayList<ColumnNum>();
            for (ColumnNum bufer:columns) {
                session = sessionFactory.openSession ( );
                Query query = session.createQuery(REPORT);
                query.setParameter("val1",bufer.getCOLUMN());
                query.setParameter("val2",0);
                bufer.setValues(query.list());
                columnNums.add(bufer);
                session.close();
            }
            return columnNums;
    }
}
