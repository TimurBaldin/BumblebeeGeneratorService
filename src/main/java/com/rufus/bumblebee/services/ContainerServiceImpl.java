package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.repository.tables.TestData;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.dto.HistoryDto;
import com.rufus.bumblebee.services.interfaces.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository repository;

    @Autowired
    public ContainerServiceImpl(ContainerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ContainerDto createContainer(String name, boolean historyOn, ReportType type) {
        Container container = new Container();
        container.setName(name);
        container.setHistoryOn(historyOn);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        container.setType(type);
        container.setCuid(UUID.randomUUID());
        return getContainerDto(repository.save(container));
    }

    @Override
    public String removeContainer(String cuid) throws NoResultException {
        Container container = repository.getContainerByCuid(cuid);
        repository.delete(container);
        return cuid;
    }

    @Override
    public ContainerDto getContainerByName(String name) {
        return getContainerDto(repository.getContainerByName(name));
    }

    private ContainerDto getContainerDto(Container container) {
        ContainerDto dto = new ContainerDto();
        dto.setCuid(container.getCuid().toString());
        dto.setName(container.getName());
        dto.setStatus(container.getStatus());
        return dto;
    }
}
