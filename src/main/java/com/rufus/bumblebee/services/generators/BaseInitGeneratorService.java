package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.generators.dto.parameters.GeneratorDescription;
import com.rufus.bumblebee.services.interfaces.InitGeneratorService;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.rufus.bumblebee.generators.utils.GeneratorsUtils.*;

public abstract class BaseInitGeneratorService<R> implements InitGeneratorService<R> {

    protected DataGenerator createGenerator(Map<String, String> values, String generatorName) throws Exception {
        GeneratorDescription description = getGeneratorDescriptionByName(generatorName);
        DataGenerator generator;

        if (description == null) {
            throw new Exception("Generator not found: " + generatorName);
        } else {
            generator = description.getGeneratorClass().newInstance();
        }

        Class<? extends DataGenerator> generatorClass = generator.getClass();
        List<GeneratorParameter> annotations = getGeneratorParameterList(generatorClass);
        for (Map.Entry<String, String> value : values.entrySet()) {
            //Проверить что существует GeneratorParameter
            GeneratorParameter parameter = getGeneratorParameterByName(value.getKey(), annotations);
            Method method = generatorClass.getMethod(parameter.setValueMethod(), String.class);
            method.invoke(String.class, value.getValue());
        }
        return generator;
    }

    abstract void validateGeneratorsRequest(R request) throws Exception;
}
