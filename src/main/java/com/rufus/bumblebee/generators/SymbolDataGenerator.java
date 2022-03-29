package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.rufus.bumblebee.generators.SymbolDataGenerator.DataMode.NUMBER;
import static com.rufus.bumblebee.generators.SymbolDataGenerator.DataMode.STRING;
import static com.rufus.bumblebee.generators.dto.parameters.GeneratorParametersRegexp.BOOLEAN_REG;
import static com.rufus.bumblebee.generators.dto.parameters.GeneratorParametersRegexp.NUMBERS_REG;
import static com.rufus.bumblebee.generators.utils.ConvertorUtils.convertStringToBoolean;
import static com.rufus.bumblebee.generators.utils.ConvertorUtils.convertStringToInt;

public class SymbolDataGenerator extends BaseGenerator {

    private final GeneratorParameters parameters;

    private static final String LEN_PARAMETER = "len";
    private static final String COUNT_PARAMETER = "count";
    private static final String MODE_PARAMETER = "mode";
    private static final String CASCADE_PARAMETER = "isCascade";

    private static final int MIN_ID_STRING = 1;
    private static final int MAX_ID_STRING = 192;
    private static final int MIN_ID_NUMERIC = 48;
    private static final int MAX_ID_NUMERIC = 58;

    public SymbolDataGenerator() {
        this.parameters = new GeneratorParameters(4)
                .addParameter(LEN_PARAMETER,
                        new GeneratorParameters.Field.Builder("The length of the text value, applied if isCascade = false", true)
                                .setRegexp(NUMBERS_REG.getRegexp())
                                .setPredicate(p -> convertStringToInt(((String) p)) > 1 && convertStringToInt(((String) p)) < 1000)
                                .build())
                .addParameter(COUNT_PARAMETER,
                        new GeneratorParameters.Field.Builder("Number of text values in the list", true)
                                .setRegexp(NUMBERS_REG.getRegexp())
                                .setPredicate(p -> convertStringToInt(((String) p)) > 1 && convertStringToInt(((String) p)) < 1000)
                                .build())
                .addParameter(MODE_PARAMETER,
                        new GeneratorParameters.Field.Builder("Maybe value STRING or NUMBER", true)
                                .setRegexp("^STRING|NUMBER$")
                                .build())
                .addParameter(CASCADE_PARAMETER,
                        new GeneratorParameters.Field.Builder("Cascading increment of values in a text expression", true)
                                .setRegexp(BOOLEAN_REG.getRegexp())
                                .build());
    }

    @Override
    public List<List<String>> getTestData(List<Map<String, String>> values) {
        List<List<String>> result = new ArrayList<>(values.size());

        for (Map<String, String> map : values) {
            int count = convertStringToInt(map.get(COUNT_PARAMETER));
            List<String> data = new ArrayList<>(count);

            int len = convertStringToInt(map.get(LEN_PARAMETER));
            SymbolDataGenerator.DataMode mode = SymbolDataGenerator.DataMode.valueOf(map.get(MODE_PARAMETER));
            boolean isCascade = convertStringToBoolean(map.get(CASCADE_PARAMETER));

            if (mode.equals(STRING)) {
                generateData(MIN_ID_STRING, MAX_ID_STRING, data, count, isCascade, len);
            }

            if (mode.equals(NUMBER)) {
                generateData(MIN_ID_NUMERIC, MAX_ID_NUMERIC, data, count, isCascade, len);
            }
            result.add(data);
        }
        return result;
    }

    @Override
    public GeneratorParameters getParameters() {
        return parameters;
    }

    enum DataMode {
        STRING("STRING"),
        NUMBER("NUMBER");
        final String key;

        DataMode(String key) {
            this.key = key;
        }
    }

    private void generateData(int startSeq, int endSeq, List<String> values, int count, boolean isCascade, int len) {
        StringBuilder testData = new StringBuilder(count);
        for (int i = 1; i <= count; i++) {
            if (isCascade) {
                for (int j = 1; j <= i; j++) {
                    testData.append((char) ThreadLocalRandom.current().nextInt(startSeq, endSeq));
                }
            } else {
                for (int j = 1; j <= len; j++) {
                    testData.append((char) ThreadLocalRandom.current().nextInt(startSeq, endSeq));
                }
            }
            values.add(testData.toString());
            testData.delete(0, i);
        }
    }

}