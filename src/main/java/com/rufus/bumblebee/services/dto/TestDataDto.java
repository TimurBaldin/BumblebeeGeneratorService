package com.rufus.bumblebee.services.dto;

import java.util.List;

public class TestDataDto {

    private String generatorName;
    private List<String> data;

    public TestDataDto(String generatorName, List<String> data) {
        this.generatorName = generatorName;
        this.data = data;
    }

    public String getGeneratorName() {
        return generatorName;
    }

    public List<String> getData() {
        return data;
    }

}
