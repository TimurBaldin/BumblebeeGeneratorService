package com.rufus.bumblebee.generators;

import java.util.Map;

public class GeneratorInformation {
    String generatorName;
    Map<String, String> values;

    public String getGeneratorName() {
        return generatorName;
    }

    public GeneratorInformation setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
        return this;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public GeneratorInformation setValues(Map<String, String> values) {
        this.values = values;
        return this;
    }
}
