package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.config.ConfigurationRepository;
import com.rufus.bumblebee.repository.tables.Container;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ContainerRepositoryTest extends ConfigurationRepository {

    @Autowired
    private ContainerRepository repository;

    @Test
    public void testSaveContainer() {
        Container saveContainer = repository.createOrUpdateContainer(getTestContainer());
        Assert.assertNotNull(saveContainer.getId());
        Assert.assertTrue(saveContainer.getId() != 0);
    }


    @Test
    public void testGetContainerById() throws NotFoundException {
        Container containerAfterSave = repository.createOrUpdateContainer(getTestContainer());
        Container containerAfterGet = repository.getContainerById(containerAfterSave.getId());

        Assert.assertNotNull(containerAfterGet);
        Assert.assertEquals(containerAfterGet.getId(), containerAfterSave.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testRemoveContainer() throws NotFoundException {
        Container containerAfterSave = repository.createOrUpdateContainer(getTestContainer());
        repository.removeContainer(containerAfterSave);
        repository.getContainerById(containerAfterSave.getId());
    }
}