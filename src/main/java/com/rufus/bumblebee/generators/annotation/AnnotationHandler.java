package com.rufus.bumblebee.generators.annotation;

import com.rufus.bumblebee.generators.BaseGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnnotationHandler {

    private static final Logger log = LoggerFactory.getLogger(AnnotationHandler.class);

    private static final Map<GeneratorDescription, List<GeneratorParameter>> generatorBeans = new HashMap<>();

    private final List<BaseGenerator> generators;

    private final ApplicationContext context;

    @Autowired
    public AnnotationHandler(List<BaseGenerator> generators, ApplicationContext context) {
        this.generators = generators;
        this.context = context;
    }

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

    public void setParameters(Field[] fields, Map<String, String> values, BaseGenerator generator) throws Exception {
        for (Field field : fields) {
            GeneratorParameter annotation = field.getAnnotation(GeneratorParameter.class);
            if (annotation != null) {
                String value = values.get(annotation.name());
                if (value == null) {
                    log.error("For one of the parameters of the {} generator, a name is not specified in the annotation GeneratorParameter", generator.getGeneratorName());
                    throw new Exception("The parameter name is not specified in the GeneratorParameter annotation for the generator: " + generator.getGeneratorName());
                }

                if (StringUtils.isNotEmpty(annotation.convertMethod())) {
                    Method method = annotation.InClass().getMethod(annotation.convertMethod(), annotation.InClass());
                    field.set(generator, method.invoke(annotation.InClass(), value));
                } else {
                    if (!annotation.InClass().equals(String.class)) {
                        Method method = annotation.InClass().getMethod("valueOf", String.class);
                        field.set(generator, method.invoke(annotation.InClass(), value));
                    } else {
                        field.set(generator, value);
                    }
                }
            } else {
                log.error("An annotation GeneratorParameter was not found for one of the fields of the generator {}", generator.getGeneratorName());
                throw new Exception("For one of the parameters , the annotation GeneratorParameter was not found , for the generator: " + generator.getGeneratorName());
            }
        }
    }

    public static Map<GeneratorDescription, List<GeneratorParameter>> getGeneratorBeans() {
        return generatorBeans;
    }
}
