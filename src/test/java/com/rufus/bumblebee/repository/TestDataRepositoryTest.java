package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.config.ConfigurationRepository;
import com.rufus.bumblebee.repository.tables.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class TestDataRepositoryTest extends ConfigurationRepository {

    @Autowired
    private TestDataRepository repository;

    @Autowired
    private ContainerRepository containerRepository;

    @Test
    public void testSaveTestData() {
        List<String> dataList = new ArrayList<>();
        dataList.add("TEST");
        TestData data = repository.saveTestData(
                dataList,
                containerRepository.createOrUpdateContainer(getTestContainer()).getId()
        );
        Assert.assertNotNull(data.getId());
        Assert.assertTrue(data.getId() != 0);
        Assert.assertNotNull(data.getValue());
    }
}