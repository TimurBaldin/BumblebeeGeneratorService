package com.rufus.bumblebee.generators.dto;

import java.util.List;
import java.util.Map;

public class GeneratorInformation {
    private String generatorName;
    private List<Map<String, String>> values;

    public String getGeneratorName() {
        return generatorName;
    }

    public GeneratorInformation setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
        return this;
    }

    public List<Map<String, String>> getValues() {
        return values;
    }

    public void setValues(List<Map<String, String>> values) {
        this.values = values;
    }
}
