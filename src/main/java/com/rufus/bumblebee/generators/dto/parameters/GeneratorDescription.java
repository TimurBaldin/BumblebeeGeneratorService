package com.rufus.bumblebee.generators.dto.parameters;

import com.rufus.bumblebee.generators.DataGenerator;
import com.rufus.bumblebee.generators.PhoneNumberGenerator;
import com.rufus.bumblebee.generators.SymbolDataGenerator;

public enum GeneratorDescription {

    SYMBOL_GENERATOR(new SymbolDataGenerator(), "SymbolGenerator", "Generator for create random values"),
    PHONE_NUMBER_GENERATOR(new PhoneNumberGenerator(), "PhoneNumberGenerator", "Generator random phone numbers");;

    private final DataGenerator generator;
    private final String name;
    private final String description;

    GeneratorDescription(DataGenerator generator, String name, String description) {
        this.generator = generator;
        this.name = name;
        this.description = description;
    }

    public DataGenerator getGenerator() {
        return generator;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
