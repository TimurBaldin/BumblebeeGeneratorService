package com.rufus.bumblebee.generators.dto.parameters;

public enum GeneratorParametersRegexp {

    NUMBERS_REG("^[0-9]+$"),
    STRINGS_REG("^[a-zA-zА-Яа-я]+$"),
    BOOLEAN_REG("^true|false|TRUE|FALSE|True|False$");

    private final String regexp;

    GeneratorParametersRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getRegexp() {
        return regexp;
    }
}
