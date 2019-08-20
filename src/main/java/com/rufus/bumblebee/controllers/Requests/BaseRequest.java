package com.rufus.bumblebee.controllers.Requests;

import javax.validation.constraints.NotNull;

public class BaseRequest {

    @NotNull
    private String containerId;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

}
