package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.TestDataContainerRequest;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.tables.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContainerService {

    private ContainerRepository repository;

    @Autowired
    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }

    public Container createTestDataContainer(TestDataContainerRequest request) {
        Container container = new Container();
        container.setName(request.getName());
        Date date = new Date();
        container.setDate(date);
        container.setUpdateDate(date);
        container.setClientRef(request.getClientRef());
        return repository.createContainer(container);
    }

    public void removeContainer(Long containerId) {
        Container container = repository.getContainerById(containerId);
        if (container != null) {
            repository.removeContainer(container);
        }
    }

}
