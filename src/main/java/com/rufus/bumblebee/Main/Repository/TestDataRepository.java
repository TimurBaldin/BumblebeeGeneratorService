package com.rufus.bumblebee.Main.Repository;

/**
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.rufus.bumblebee.Main.Columns.Columns;
import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import com.rufus.bumblebee.Main.Repository.DAO.BaseRepository;
import com.rufus.bumblebee.Main.Repository.DAO.Repository;
import com.rufus.bumblebee.Main.Tables.StringTableBufer;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class TestDataRepository extends Repository implements BaseRepository<Columns, TypeTestData> {

    private final String REPORT = "SELECT value FROM com.rufus.bumblebee.Main.Tables.StringTableBufer where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID and alive=:live";
    private final String DEL_TEST_DATA = "UPDATE com.rufus.bumblebee.Main.Tables.StringTableBufer SET alive='false' where ColumnName=:COLUMNNAME and user_id=:CLIENT_ID";

    @Transactional
    @Override
    public boolean create(List<TypeTestData> values, String COLUMN_NAME) {
        boolean status = true;
        for (int i = 0; i <= values.size() - 1; i++) {
            StringTableBufer bufer = new StringTableBufer();
            bufer.setValue(String.valueOf(values.get(i).getValue()));
            bufer.setColumnName(COLUMN_NAME);
            bufer.setAlive(true);
            getEm().persist(bufer);

        }
        values.clear();
        return status;
    }

    @Override
    public List<Columns> get(List<Columns> columns) {
        List<Columns> column = new ArrayList<Columns>();
        for (Columns bufer : columns) {
            Query query = getEm().createQuery(REPORT);
            query.setParameter("COLUMNNAME", bufer.getCOLUMN());
            query.setParameter("CLIENT_ID", 0);
            query.setParameter("live", true);
            bufer.setReport(query.getResultList());
            column.add(bufer);
        }
        return column;
    }

    @Transactional
    @Override
    public boolean delete(List<Columns> columns) {
        int delrow = 0;
        boolean status = false;
        for (Columns bufer : columns) {
            Query query = getEm().createQuery(DEL_TEST_DATA);
            query.setParameter("COLUMNNAME", bufer.getCOLUMN());
            query.setParameter("CLIENT_ID", 0);
            delrow += query.executeUpdate();
        }

        if (delrow != columns.size() - 1) {
            status = true;
        }
        return status;

    }


}
