package com.rufus.bumblebee.services.dto;

import java.util.List;

public class HistoryDto {

    private String containerName;
    private ContainerStatus status;
    private List<String> generators;

    public HistoryDto(String containerName, ContainerStatus status, List<String> generators) {
        this.containerName = containerName;
        this.status = status;
        this.generators = generators;
    }

    public String getContainerName() {
        return containerName;
    }

    public ContainerStatus getStatus() {
        return status;
    }

    public List<String> getGenerators() {
        return generators;
    }
}
