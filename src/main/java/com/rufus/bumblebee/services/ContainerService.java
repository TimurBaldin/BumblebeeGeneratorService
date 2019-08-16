package com.rufus.bumblebee.services;

import com.rufus.bumblebee.container.TestDataContainer;
import com.rufus.bumblebee.factories.ContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerService {

    private ContainerFactory factory;

    @Autowired
    public ContainerService(ContainerFactory factory) {
        this.factory = factory;
    }

    public TestDataContainer createTestDataContainer(String containerName) {
        TestDataContainer container = factory.getContainer();
        container.setContainerName(containerName);
        return container;
    }


}
