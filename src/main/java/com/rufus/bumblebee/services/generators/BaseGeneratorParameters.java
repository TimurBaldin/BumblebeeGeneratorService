package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.generators.annotation.GeneratorParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class BaseGeneratorParameters<A> implements GeneratorParametersService<A> {

    protected void validateAnnotation(Annotation annotation, Map<String, String> values) {
        GeneratorParameter annotation1 = field.getAnnotation(GeneratorParameter.class);
        if (annotation == null) {
            log.error("An annotation GeneratorParameter was not found for one of the fields of the generator {}", generator.getGeneratorName());
            throw new Exception("For one of the parameters , the annotation GeneratorParameter was not found , for the generator: " + generator.getGeneratorName());
        }
        if (values.get(annotation.name()) == null) {
            log.error("For one of the parameters of the {} generator, a name is not specified in the annotation GeneratorParameter", generator.getGeneratorName());
            throw new Exception("The parameter name is not specified in the GeneratorParameter annotation for the generator: " + generator.getGeneratorName());
        }
    }

    protected List<Field> getAnnotationFields(List<Field> fields, Annotation annotation) {
        List<Field> result = new ArrayList<>();
        fields.forEach(field -> {
            if (field.getAnnotation(annotation.getClass()) != null) {
                result.add(field);
            }
        });
        return result;
    }


}
