package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.config.ConfigurationRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import com.rufus.bumblebee.repositoryV2.TestDataRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestDataRepositoryTest extends ConfigurationRepository {

    @Autowired
    private TestDataRepository repository;

    @Autowired
    private ContainerRepository containerRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testSaveTestData() {
        List<Map<String, List<String>>> data = new ArrayList<>();
        data.add(Collections.singletonMap(
                "SymbolGenerator", Collections.singletonList("test")
        ));
        Container container = containerRepository.createOrUpdateContainer(getTestContainer());
        //repository.save()data, container.getId());

        TestData testData = em.find(TestData.class, container.getId());
        Assert.assertNotNull(testData);
        Assert.assertEquals("SymbolGenerator", testData.getGeneratorName());
    }
}