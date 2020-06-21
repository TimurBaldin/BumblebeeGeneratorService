package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.containers.TestDataContainerRequest;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.tables.Container;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class ContainerService {

    private final ContainerRepository repository;

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
        return repository.createContainer(container);
    }


    public void removeContainer(Long containerId) throws InvalidDataException {
        repository.removeContainer(containerId);
    }

}
