package com.rufus.bumblebee.Main.Controllers.Requests;

public class TestDataContainerRequest {

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
