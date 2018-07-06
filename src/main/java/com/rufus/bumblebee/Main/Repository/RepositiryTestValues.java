package com.rufus.bumblebee.Main.Repository;


import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.DAO.BaseRepository;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import com.rufus.bumblebee.Main.Tables.StringTableBufer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RepositiryTestValues implements BaseRepository<Columns,TypeTestData>{
    private final String REPORT="SELECT value FROM com.rufus.bumblebee.Main.Tables.StringTableBufer where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID and alive=:live";
    private final String DEL_TEST_DATA="UPDATE com.rufus.bumblebee.Main.Tables.StringTableBufer SET alive='false' where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID";
    private SessionFactory sessionFactory = SessionUntil.INSTANCE.getInstance();
    private Session session;
    @Override
    public void create(List<TypeTestData> values, String COLUMN_NAME) {
        for (int i = 0; i <= values.size() - 1; i++) {
            session = sessionFactory.openSession();
            try {
                Transaction transaction = session.beginTransaction();
                StringTableBufer bufer = new StringTableBufer();
                bufer.setValue(String.valueOf(values.get(i).getValue()));
                bufer.setColumnName(COLUMN_NAME);
                bufer.setAlive(true);
                session.save(bufer);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                session.close();

            }
        }
        values.clear();
    }
    @Override
    public List<Columns> get(List<Columns> columns) {
        List<Columns> column = new ArrayList<Columns>();
        for (Columns bufer : columns) {
            session = sessionFactory.openSession();
            Query query = session.createQuery(REPORT);
            query.setParameter("COLUMNNAME", bufer.getCOLUMN());
            query.setParameter("CLIENT_ID", 0);
            query.setParameter("live",true);
            bufer.setReport(query.list());
            column.add(bufer);
            session.close();
            }

        return column;
    }
    @Override
    public boolean delete(List<Columns> columns){
        int delrow=0;
        boolean status=false;
        for (Columns bufer : columns) {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(DEL_TEST_DATA);
            query.setParameter("COLUMNNAME", bufer.getCOLUMN());
            query.setParameter("CLIENT_ID", 0);
            delrow+=query.executeUpdate();
            transaction.commit();
            session.close();
        }
        if (delrow==0){
            return status;
        }else {
            status=true;
            return status;
        }

    }



}
