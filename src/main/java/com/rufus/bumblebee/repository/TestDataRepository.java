package com.rufus.bumblebee.repository;

/*
 * Class : класс для CRUD операций с тестовыми данными
 *
 * @version : 0.0.1
 * @author : Baldin Timur
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rufus.bumblebee.repository.tables.TestData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TestDataRepository {

    @PersistenceContext
    EntityManager em;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public void saveTestData(List<Map<String, List<String>>> dataList, Long containerRef) {
        for (Map<String, List<String>> data : dataList) {
            data.forEach((key, value) -> {
                TestData testData = new TestData();
                testData.setGeneratorName(key);
                testData.setContainerRef(containerRef);
                testData.setValue(gson.toJson(value));
                em.persist(testData);
            });
        }
    }
}
