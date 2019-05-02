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
import com.rufus.bumblebee.Main.Tables.TestData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class TestDataRepository implements BaseRepository<Columns, TypeTestData> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean create(List<TypeTestData> values, String COLUMN_NAME) {
        for (int i = 0; i <= values.size() - 1; i++) {
            TestData data = new TestData();
            data.setValue(String.valueOf(values.get(i).getValue()));
            data.setColumnName(COLUMN_NAME);
            data.setAlive(true);
            em.persist(data);
        }
        values.clear();
        return true;
    }

    @Override
    public List<Columns> get(List<Columns> columns) {
        List<Columns> result = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TestData> testDataCriteriaQuery =
                criteriaBuilder.createQuery(TestData.class);
        Root<TestData> testDataRoot = testDataCriteriaQuery.from(TestData.class);
        for (Columns bufer : columns) {
            testDataCriteriaQuery.select(testDataRoot.get("value"));
            Predicate[] predicates = new Predicate[3];
            predicates[0] = criteriaBuilder.equal(testDataRoot.get("ColumnName"), bufer.getColumn());
            predicates[1] = criteriaBuilder.equal(testDataRoot.get("alive"), true);
            predicates[2] = criteriaBuilder.equal(testDataRoot.get("user_id"), 0);
            testDataCriteriaQuery.where(predicates);
            TypedQuery<TestData> query = em.createQuery(testDataCriteriaQuery);
            bufer.setReport(query.getResultList());
            result.add(bufer);
        }
        return result;
    }

    @Override
    public boolean delete(List<Columns> columns) {
        int delrow = 0;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<TestData> builderCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(TestData.class);
        Root<TestData> root = builderCriteriaUpdate.from(TestData.class);
        builderCriteriaUpdate.set("alive", false);
        boolean status = false;
        for (Columns bufer : columns) {
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.equal(root.get("ColumnName"), bufer.getColumn());
            predicates[1] = criteriaBuilder.equal(root.get("user_id"), 0);
            builderCriteriaUpdate.where(predicates);
            delrow += em.createQuery(builderCriteriaUpdate).executeUpdate();
        }
        if (delrow != columns.size() - 1) {
            status = true;
        }
        return status;
    }

}
