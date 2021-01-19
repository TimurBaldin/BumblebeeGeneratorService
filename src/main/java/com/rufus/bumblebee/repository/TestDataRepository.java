package com.rufus.bumblebee.repository;

/*
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.google.gson.Gson;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.repository.tables.TestData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TestDataRepository {

    @PersistenceContext
    EntityManager em;

    public TestData saveTestData(List<TypeTestData> dataList, Long containerRef) {
        TestData testData = new TestData();
        testData.setValue(new Gson().toJson(dataList));
        testData.setContainerRef(containerRef);
       return em.merge(testData);
    }
}
