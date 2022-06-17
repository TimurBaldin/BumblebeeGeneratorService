package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorParameters;

import java.util.List;
import java.util.Map;

public interface DataGenerator {

    List<String> getTestData(List<Map<String, String>> values);

    void validate(List<Map<String, String>> values) throws Exception;

    GeneratorParameters getParameters();

}
