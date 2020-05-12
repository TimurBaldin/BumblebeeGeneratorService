package com.rufus.bumblebee.repository;

/**
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.tables.TestData;
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

    public void saveTestData(List<TypeTestData> dataList, Long containerRef) {
        for (TypeTestData data : dataList) {
            TestData testData = new TestData();
            testData.setValue(String.valueOf(data.getValue()));
            testData.setContainerRef(containerRef);
            em.persist(testData);
        }

    }

}
