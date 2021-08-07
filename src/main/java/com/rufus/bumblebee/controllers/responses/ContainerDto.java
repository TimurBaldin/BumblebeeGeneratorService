package com.rufus.bumblebee.controllers.responses;

import com.rufus.bumblebee.repository.ContainerStatus;

public class ContainerDto {
    private Long id;
    private String name;
    private ContainerStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ContainerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
