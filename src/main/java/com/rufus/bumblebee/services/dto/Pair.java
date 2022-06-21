package com.rufus.bumblebee.services.dto;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;

import java.util.List;
import java.util.Map;

public class Pair {
    private final GeneratorDescription description;
    private final List<Map<String, String>> values;

    public Pair(GeneratorDescription description, List<Map<String, String>> values) {
        this.description = description;
        this.values = values;
    }

    public GeneratorDescription getDescription() {
        return description;
    }

    public List<Map<String, String>> getValues() {
        return values;
    }
}
