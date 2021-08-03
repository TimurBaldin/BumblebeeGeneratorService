package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.tables.Container;
import javassist.NotFoundException;
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

    public ContainerDto createTestDataContainer(String name, Boolean auth) {
        Container container = new Container();
        container.setName(name);
        container.setIsAuthenticated(auth);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        return getContainerDto(repository.createOrUpdateContainer(container));
    }

    public Long removeContainer(Long containerId) throws NotFoundException {
        Container container = repository.getContainerById(containerId);
        repository.removeContainer(container);
        return containerId;
    }

    private ContainerDto getContainerDto(Container container) {
        ContainerDto dto = new ContainerDto();
        dto.setId(container.getId());
        dto.setName(container.getName());
        dto.setStatus(container.getStatus());
        return dto;
    }
}
