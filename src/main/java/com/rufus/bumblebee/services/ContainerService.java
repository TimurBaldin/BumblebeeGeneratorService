package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.repository.tables.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class ContainerService {

    private final ContainerRepository repository;

    @Autowired
    public ContainerService(ContainerRepository repository) {
        this.repository = repository;
    }

    public ContainerDto createContainer(String name, boolean auth, ReportType type) {
        Container container = new Container();
        container.setName(name);
        container.setAuthenticated(auth);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        container.setType(type);
        container.setCuid(UUID.randomUUID());
        return getContainerDto(repository.createOrUpdateContainer(container));
    }

    public String removeContainer(String cuid) throws NoResultException {
        Container container = repository.getContainerById(cuid);
        repository.removeContainer(container);
        return cuid;
    }

    private ContainerDto getContainerDto(Container container) {
        ContainerDto dto = new ContainerDto();
        dto.setCuid(container.getCuid().toString());
        dto.setName(container.getName());
        dto.setStatus(container.getStatus());
        return dto;
    }
}
