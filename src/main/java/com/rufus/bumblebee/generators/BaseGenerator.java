package com.rufus.bumblebee.generators;


import com.rufus.bumblebee.generators.dto.parameters.GeneratorParameters;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

abstract class BaseGenerator implements DataGenerator {

    @Override
    public void validate(List<Map<String, String>> values) throws Exception {
        containsRequiredFields(values);

        Map<String, GeneratorParameters.Field> parameters = getParameters().getFields();

        for (Map<String, String> map : values) {

            for (Map.Entry<String, String> field : map.entrySet()) {
                GeneratorParameters.Field parameter = parameters.getOrDefault(field.getKey(), null);

                if (parameter == null) {
                    throw new Exception("Параметр не найден " + field.getKey());
                }

                if (parameter.getRegexp() != null) {
                    if (!Pattern.matches(parameter.getRegexp(), field.getValue())) {
                        throw new Exception("Параметр с именем: " + field.getKey() + " не прошел проверку регулярным выржением " + parameter.getRegexp());
                    }
                }

                if (parameter.getPredicate() != null) {
                    if (parameter.getPredicate().negate().test(field.getValue())) {
                        throw new Exception("Параметр с именем: " + field.getKey() + " не прошел валидацию по условию Predicate");
                    }
                }

            }

        }

    }

    private void containsRequiredFields(List<Map<String, String>> values) throws Exception {
        for (Map.Entry<String, GeneratorParameters.Field> field : getRequiredFields()) {
            for (Map<String, String> map : values) {
                if (!map.containsKey(field.getKey())) {
                    throw new Exception("Не передан обязательный параметр " + field.getKey());
                }
            }
        }
    }

    private List<Map.Entry<String, GeneratorParameters.Field>> getRequiredFields() {
        return getParameters().getFields().entrySet().stream()
                .filter(s -> s.getValue().isRequired()).collect(Collectors.toList());
    }

}
