package com.rufus.bumblebee.generators.factories;

import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Component
public class DefaultGeneratorFactory extends BaseGeneratorFactory {

    @Override
    public DataGenerator createGenerator(Map<String, String> values, DataGenerator generator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class generatorClass = generator.getClass();
        List<GeneratorParameter> annotations = getAnnotationList(generatorClass);
        for (Map.Entry<String, String> value : values.entrySet()) {
            GeneratorParameter parameter = getGeneratorParameterByKey(value.getKey(), annotations).get();
            Method method = generatorClass.getMethod(parameter.setValueMethod(), String.class);
            method.invoke(String.class, value.getValue());
        }
        return generator;
    }
}
