package com.rufus.bumblebee.repository;


import com.rufus.bumblebee.repository.config.ConfigurationRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class TestDataRepositoryTest extends ConfigurationRepository {

    @Autowired
    private TestDataRepository repository;

    @Autowired
    private ContainerRepository containerRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testSaveTestData() {
        Container container = containerRepository.save(getTestContainer());

        List<TestData> testData=new ArrayList<>();
        testData.add(new TestData("TEST",container.getId(),"SymbolGenerator"));

        Iterable<TestData> result=repository.saveAll(testData);

        result.forEach(
                r->System.out.println("$$$$: "+r.getId())
        );

       // TestData testData = em.find(TestData.class, container.getId());
      //  Assert.assertNotNull(testData);
       // Assert.assertEquals("SymbolGenerator", testData.getGeneratorName());


    }
}