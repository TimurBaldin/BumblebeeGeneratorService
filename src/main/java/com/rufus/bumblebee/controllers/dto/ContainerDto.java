package com.rufus.bumblebee.controllers.dto;

import com.rufus.bumblebee.services.dto.ContainerStatus;

public class ContainerDto {
    private String cuid;
    private String name;
    private ContainerStatus status;

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContainerStatus getStatus() {
        return status;
    }

    public void setStatus(ContainerStatus status) {
        this.status = status;
    }
}
