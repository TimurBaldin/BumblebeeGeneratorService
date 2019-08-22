package com.rufus.bumblebee.repository;

/**
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.rufus.bumblebee.container.Container;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.repository.DAO.BaseRepository;
import com.rufus.bumblebee.tables.TestData;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestDataRepository implements BaseRepository<Container, TypeTestData> {

    private final String REPORT = "SELECT value FROM com.rufus.bumblebee.Main.Tables.StringTableBufer where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID and alive=:live";
    private final String DEL_TEST_DATA = "UPDATE com.rufus.bumblebee.Main.Tables.StringTableBufer SET alive='false' where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID";

    @Override
    public boolean create(List<TypeTestData> values, String COLUMN_NAME) {
        boolean status = true;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (int i = 0; i <= values.size() - 1; i++) {
                TestData bufer = new TestData();
                bufer.setValue(String.valueOf(values.get(i).getValue()));
                bufer.setColumnName(COLUMN_NAME);
                bufer.setAlive(true);
                session.save(bufer);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            status = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return status;
    }

    @Override
    public List<Container> get(List<Container> columns) {
        List<Container> column = new ArrayList<Container>();
        Session session = sessionFactory.openSession();
        for (Container bufer : columns) {
            Query query = session.createQuery(REPORT);
            query.setParameter("COLUMNNAME", bufer.getContainerName());
            query.setParameter("CLIENT_ID", 0);
            query.setParameter("live", true);
            bufer.setReport(query.list());
            column.add(bufer);
        }
        session.close();
        return column;
    }

    @Override
    public boolean delete(List<Container> columns) {
        int delrow = 0;
        boolean status = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Container bufer : columns) {
                Query query = session.createQuery(DEL_TEST_DATA);
                query.setParameter("COLUMNNAME", bufer.getContainerName());
                query.setParameter("CLIENT_ID", 0);
                delrow += query.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            status = false;

        } finally {
            session.close();
        }
        if (delrow != columns.size() - 1) {
            status = true;
        }
        return status;

    }


}
