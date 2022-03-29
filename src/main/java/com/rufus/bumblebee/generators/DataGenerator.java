package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorParameters;

import java.util.List;
import java.util.Map;

public interface DataGenerator {

    List<List<String>> getTestData(List<Map<String, String>> values);

    boolean validate(List<Map<String, String>> values) throws Exception;

    GeneratorParameters getParameters();

}
