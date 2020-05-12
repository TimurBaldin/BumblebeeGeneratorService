package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.tables.Container;
import com.sun.media.sound.InvalidDataException;
import org.eclipse.core.runtime.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

public class ContainerRepositoryTest extends BaseRepositoryTest {

    @Autowired
    public ContainerRepository repository;

    private static Container container;
    private static final String CONTAINER_NAME = "CONTAINER_NAME_TEST";

    @BeforeClass
    public static void preparationData() {
        container = new Container();
        Date date = new Date();
        container.setName(CONTAINER_NAME);
        container.setDate(date);
        container.setUpdateDate(date);
    }

    @Test
    public void testSaveContainer() {
        Container savedContainer = repository.createContainer(container);
        Assert.isTrue(Objects.nonNull(savedContainer));
    }

    @Test
    public void testGetContainer() throws InvalidDataException {
        Container savedContainer = repository.createContainer(container);
        Assert.isTrue(Objects.nonNull(
                repository.getContainerById(savedContainer.getId())
        ));
    }
}