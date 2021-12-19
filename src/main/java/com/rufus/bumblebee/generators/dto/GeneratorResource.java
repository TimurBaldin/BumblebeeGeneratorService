package com.rufus.bumblebee.generators.dto;

import java.util.List;
import java.util.Map;

public class GeneratorResource {

    private final Map<String, List<String>> data;

    public GeneratorResource(Map<String, List<String>> data) {
        this.data = data;
    }

    public Map<String, List<String>> getData() {
        return data;
    }
}
