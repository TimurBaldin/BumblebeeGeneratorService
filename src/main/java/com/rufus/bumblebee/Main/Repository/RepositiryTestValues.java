package com.rufus.bumblebee.Main.Repository;

/**
 * Class : класс для CRUD операций с тестовыми данными
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.DAO.BaseRepository;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import com.rufus.bumblebee.Main.Tables.StringTableBufer;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
public class RepositiryTestValues implements BaseRepository<Columns,TypeTestData>{
    private final String REPORT="SELECT value FROM com.rufus.bumblebee.Main.Tables.StringTableBufer where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID and alive=:live";
    private final String DEL_TEST_DATA="UPDATE com.rufus.bumblebee.Main.Tables.StringTableBufer SET alive='false' where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID";
    @Override
    public boolean create(List<TypeTestData> values, String COLUMN_NAME) {
        boolean status=true;
        Session   session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
        for (int i = 0; i <= values.size() - 1; i++) {
            StringTableBufer bufer = new StringTableBufer();
                bufer.setValue(String.valueOf(values.get(i).getValue()));
                bufer.setColumnName(COLUMN_NAME);
                bufer.setAlive(true);
                session.save(bufer);
                if ( i % 20 == 0 ) {
                    session.flush();
                    session.clear();
                }
                }
            transaction.commit();
                }catch (Exception ex) {
            ex.printStackTrace();
            status=false;
            session.getTransaction().rollback();
        } finally {
              session.close();
        }
        values.clear();
return status;
    }
    @Override
    public List<Columns> get(List<Columns> columns) {
        List<Columns> column = new ArrayList<Columns>();
        Session  session = sessionFactory.openSession();
        for (Columns bufer : columns) {
            Query query = session.createQuery(REPORT);
            query.setParameter("COLUMNNAME", bufer.getCOLUMN());
            query.setParameter("CLIENT_ID", 0);
            query.setParameter("live",true);
            bufer.setReport(query.list());
            column.add(bufer);
            }
        session.close();
        return column;
    }
    @Override
    public boolean delete(List<Columns> columns){
        int delrow=0;
        boolean status=false;
        Session   session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Columns bufer : columns) {
                Query query = session.createQuery(DEL_TEST_DATA);
                query.setParameter("COLUMNNAME", bufer.getCOLUMN());
                query.setParameter("CLIENT_ID", 0);
                delrow += query.executeUpdate();
                }
        }catch (Exception ex){
            ex.printStackTrace();
            status=false;

        }finally {
            session.close();
        }
        if (delrow != columns.size() - 1) {
            status = true;
        }
        return status;

    }




}
