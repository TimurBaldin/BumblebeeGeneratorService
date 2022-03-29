package com.rufus.bumblebee.generators.utils;

import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorsUtils {

    private GeneratorsUtils() {
    }

    public static List<GeneratorParameter> getGeneratorParametersByClass(Class<? extends DataGenerator> generatorClass) {
        return Stream.of(generatorClass.getAnnotations())
                .filter(annotation -> annotation.equals(GeneratorParameter.class))
                .map(annotation -> (GeneratorParameter) annotation)
                .collect(Collectors.toList());
    }

    public static GeneratorDescription getGeneratorDescriptionByName(String name) {
        return Stream.of(GeneratorDescription.values()).filter(g -> g.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static List<GeneratorParameter> getGeneratorParameterList(Class<? extends DataGenerator> generatorClass) {
        return Arrays.stream(generatorClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(GeneratorParameter.class) != null)
                .map(field -> field.getAnnotation(GeneratorParameter.class))
                .collect(Collectors.toList());
    }

    public static GeneratorParameter getGeneratorParameterByName(String key, List<GeneratorParameter> annotations) {
        return annotations.stream()
                .filter(annotation -> annotation.name().equalsIgnoreCase(key))
                .findFirst().get();
    }

}
