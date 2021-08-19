package com.rufus.bumblebee.utils;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.generators.GeneratorInformation;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;

import javax.validation.ValidationException;
import java.util.Map;

import static com.rufus.bumblebee.generators.annotation.AnnotationHandler.getGeneratorBeans;

public final class GeneratorValidatorUtils {

    private GeneratorValidatorUtils() {
    }

    private static boolean checkGeneratorParameters(Map<String, String> generatorParameters, String generatorName) {
        for (GeneratorDescription description : getGeneratorBeans().keySet()) {
            if (description.generatorName().equals(generatorName)) {
                return getGeneratorBeans().get(description)
                        .stream()
                        .filter(s -> generatorParameters.containsKey(s.name()))
                        .count() == getGeneratorBeans().get(description).size();
            }
        }
        return false;
    }

    private static boolean checkGenerator(String generatorName) {
        return getGeneratorBeans().keySet()
                .stream().anyMatch(s -> s.generatorName().equals(generatorName));
    }

    public static void validateGeneratorsRequest(GeneratorsRequest request) throws ValidationException {
        for (GeneratorInformation information : request.getGeneratorInfo()) {
            if (!checkGenerator(information.getGeneratorName())) {
                throw new ValidationException("Invalid value passed generatorName: " + information.getGeneratorName());
            }
            if (!checkGeneratorParameters(information.getValues(), information.getGeneratorName())) {
                throw new ValidationException("Invalid value passed generatorParameters: " + information.getValues());
            }
        }
    }
}
