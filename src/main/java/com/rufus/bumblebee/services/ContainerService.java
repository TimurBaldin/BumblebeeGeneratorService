package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.ContainerRequest;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.tables.Container;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ContainerService {

    private final ContainerRepository repository;

    @Autowired
    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }

    public Container createTestDataContainer(ContainerRequest request) {
        Container container = new Container();
        container.setName(request.getName());
        LocalDateTime date = LocalDateTime.now();
        container.setDate(date);
        container.setUpdateDate(date);
        container.setStatus(ContainerStatus.NEW);
        return repository.createOrUpdateContainer(container);
    }


    public void removeContainer(Long containerId) throws InvalidDataException {
        repository.removeContainer(containerId);
    }
}
