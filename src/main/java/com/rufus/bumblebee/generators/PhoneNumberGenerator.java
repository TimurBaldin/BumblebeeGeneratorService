package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.dto.parameters.GeneratorParameters;

import java.util.List;
import java.util.Map;

import static com.rufus.bumblebee.generators.dto.parameters.GeneratorParametersRegexp.BOOLEAN_REG;
import static com.rufus.bumblebee.generators.dto.parameters.GeneratorParametersRegexp.NUMBERS_REG;
import static com.rufus.bumblebee.generators.utils.ConvertorUtils.convertStringToInt;

public class PhoneNumberGenerator extends BaseGenerator {

    private final GeneratorParameters parameters;

    private static final String LEN_PARAMETER = "len";
    private static final String COUNT_PARAMETER = "count";
    private static final String REAL_COUNTRY_CODE_PARAMETER = "realCountryCode";


    public PhoneNumberGenerator() {
        this.parameters = new GeneratorParameters(3)
                .addParameter(LEN_PARAMETER,
                        new GeneratorParameters.Field.Builder("The max length of phone number", true)
                                .setRegexp(NUMBERS_REG.getRegexp())
                                .setPredicate(p -> convertStringToInt(((String) p)) > 1 && convertStringToInt(((String) p)) < 1000)
                                .build())
                .addParameter(COUNT_PARAMETER,
                        new GeneratorParameters.Field.Builder("Number of text values in the list", true)
                                .setRegexp(NUMBERS_REG.getRegexp())
                                .setPredicate(p -> convertStringToInt(((String) p)) > 1 && convertStringToInt(((String) p)) < 1000)
                                .build())
                .addParameter(REAL_COUNTRY_CODE_PARAMETER,
                        new GeneratorParameters.Field.Builder("Cascading increment of values in a text expression", true)
                                .setRegexp(BOOLEAN_REG.getRegexp())
                                .build());
    }

    @Override
    public List<String> getTestData(List<Map<String, String>> values) {
        return null;
    }

    @Override
    public GeneratorParameters getParameters() {
        return parameters;
    }
}
