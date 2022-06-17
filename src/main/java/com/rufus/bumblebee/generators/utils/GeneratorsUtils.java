package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GeneratorsUtils {

    private GeneratorsUtils() {
    }

    public static GeneratorDescription getGeneratorDescription(String generatorName) throws Exception {
        return Arrays.stream(GeneratorDescription.values())
                .filter(g -> g.getName().equalsIgnoreCase(generatorName))
                .findFirst()
                .orElseThrow(() -> new Exception("Invalid value passed generatorName: " + generatorName));
    }

    public static void checkGeneratorParameters(List<Map<String, String>> generatorParameters, GeneratorDescription generatorDescription) throws Exception {
        generatorDescription.getGenerator().validate(generatorParameters);
    }
}
