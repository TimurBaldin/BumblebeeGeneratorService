package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.ReportType;
import com.rufus.bumblebee.controllers.dto.ContainerDto;
import com.rufus.bumblebee.repository.interfaces.ContainerRepository;
import com.rufus.bumblebee.repository.tables.Container;
import com.rufus.bumblebee.services.dto.ContainerStatus;
import com.rufus.bumblebee.services.interfaces.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository repository;

    @Autowired
    public ContainerServiceImpl(ContainerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ContainerDto createContainer(String name, boolean historyOn, ReportType type) throws Exception {
        Container container = new Container();
        container.setName(name);
        container.setHistoryOn(historyOn);
        container.setDate(LocalDateTime.now());
        container.setStatus(ContainerStatus.NEW);
        container.setType(type);
        container.setCuid(UUID.randomUUID());

        try {
            return getContainerDto(repository.save(container));
        } catch (DataAccessException exception) {
            throw new Exception("Error in the create container operation for name: " + name, exception);
        }
    }

    @Override
    public String removeContainer(String cuid) throws Exception {
        try {
            Container container = repository.getContainerByCuid(cuid);
            repository.delete(container);
        } catch (DataAccessException exception) {
            throw new Exception("Error in the remove container operation for cuid: " + cuid, exception);
        }
        return cuid;
    }

    @Override
    public ContainerDto getContainerByName(String name) throws Exception {
        try {
            return getContainerDto(repository.getContainerByName(name));
        } catch (DataAccessException exception) {
            throw new Exception("Error in the get container by name operation for name: " + name, exception);
        }
    }

    private ContainerDto getContainerDto(Container container) {
        ContainerDto dto = new ContainerDto();
        dto.setCuid(container.getCuid().toString());
        dto.setName(container.getName());
        dto.setStatus(container.getStatus());
        return dto;
    }
}
