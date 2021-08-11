package com.rufus.bumblebee.generators;

import java.util.Map;

public class GeneratorInformation {
    String generatorName;
    Map<String, String> values;

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
