package com.rufus.bumblebee.controllers.Requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestDataContainerRequest {

    @NotNull(message = "Имя должно быть задано")
    @Size(min = 1, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestDataContainerRequest{" +
                "name='" + name + '\'' +
                '}';
    }

}
