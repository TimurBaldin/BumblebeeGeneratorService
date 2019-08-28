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
        container.setDate(new Date());
        container.setUpdateDate(new Date());
        container.setClientRef(request.getClientRef());
        return repository.create(container);
    }

}
