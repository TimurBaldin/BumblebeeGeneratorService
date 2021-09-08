package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.repository.config.ConfigurationRepository;
import com.rufus.bumblebee.repository.tables.Container;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;

@Ignore
//TODO выпилить EmbeddedPostgres
public class ContainerRepositoryTest extends ConfigurationRepository {

    @Autowired
    private ContainerRepository repository;

    @Test
    public void testSaveContainer() {
        Container saveContainer = repository.save(getTestContainer());
        Assert.assertNotNull(saveContainer.getId());
        Assert.assertTrue(saveContainer.getId() != 0);
    }

    @Test
    public void testGetContainerById() throws NoResultException {
        Container containerAfterSave = repository.save(getTestContainer());
        Container containerAfterGet = repository.getContainerByCuid(containerAfterSave.getCuid().toString());

        Assert.assertNotNull(containerAfterGet);
        Assert.assertEquals(containerAfterGet.getId(), containerAfterSave.getId());
    }

    @Test(expected = NoResultException.class)
    public void testRemoveContainer() throws NoResultException {
        Container containerAfterSave = repository.save(getTestContainer());
        repository.delete(containerAfterSave);
        repository.getContainerByCuid(containerAfterSave.getCuid().toString());
    }
}
