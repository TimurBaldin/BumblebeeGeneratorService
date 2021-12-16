package com.rufus.bumblebee.generators.factories;

import com.rufus.bumblebee.generators.annotation.GeneratorParameter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseGeneratorFactory implements DataGeneratorFactory {

    protected List<GeneratorParameter> getAnnotationList(Class generatorClass) {
        return Arrays.stream(generatorClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(GeneratorParameter.class) != null)
                .map(field -> field.getAnnotation(GeneratorParameter.class)).collect(Collectors.toList());
    }

    protected Optional<GeneratorParameter> getGeneratorParameterByKey(String key, List<GeneratorParameter> annotations) {
        return annotations.stream().filter(annotation -> annotation.name().equalsIgnoreCase(key)).findFirst();
    }
}
