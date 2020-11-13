package com.rufus.bumblebee.generators.annotation;

import com.rufus.bumblebee.generators.BaseGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class AnnotationHandler {

    private static final Map<GeneratorDescription, List<GeneratorParameter>>
            generatorBeans = new HashMap<>();

    @Autowired
    private final List<BaseGenerator> generators;

    @Autowired
    private final ApplicationContext context;

    @PostConstruct
    public void init() {
        for (BaseGenerator generator : generators) {
            Class<?> bean = generator.getClass();
            if (bean.isAnnotationPresent(GeneratorDescription.class) && haveTestGeneratorParameters(bean)) {
                generatorBeans.put(
                        bean.getAnnotation(GeneratorDescription.class),
                        getGeneratorParameters(bean)
                );
            }
        }
    }


    private boolean haveTestGeneratorParameters(Class<?> generatorClass) {
        for (Field field : generatorClass.getDeclaredFields()) {
            if (field.getAnnotation(GeneratorParameter.class) != null) {
                return true;
            }
        }
        return false;
    }

    private List<GeneratorParameter> getGeneratorParameters(Class<?> generatorClass) {
        List<GeneratorParameter> parameters = new ArrayList<>();
        for (Field field : generatorClass.getDeclaredFields()) {
            GeneratorParameter annotation = field.getAnnotation(GeneratorParameter.class);
            if (annotation != null) {
                parameters.add(annotation);
            }
        }
        return parameters;
    }

    public Object getBeanByName(String generatorName) {
        for (GeneratorDescription description : generatorBeans.keySet()) {
            if (description.generatorName().equals(generatorName)) {
                return context.getBean(description.generatorClass());
            }
        }
        return new Object();
    }

    public void setParameters(Field[] fields, Map<String, String> values, BaseGenerator generator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Field field : fields) {
            GeneratorParameter annotation = field.getAnnotation(GeneratorParameter.class);
            if (annotation != null) {
                String value = values.get(annotation.name());
                if (value == null) {
                    return;
                }
                if (!annotation.InClass().equals(String.class)) {
                    Method method = annotation.InClass().getMethod("valueOf", String.class);
                    field.set(generator, method.invoke(annotation.InClass(), value));
                } else {
                    field.set(generator, value);
                }

            }
        }
    }

    public static Map<GeneratorDescription, List<GeneratorParameter>> getGeneratorBeans() {
        return generatorBeans;
    }
}
