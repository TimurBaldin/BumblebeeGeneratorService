package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.annotation.GeneratorDescription;

import java.util.List;
import java.util.Optional;

@GeneratorDescription(
        generatorName = "PhoneNumberGenerator",
        generatorClass = PhoneNumberGenerator.class,
        description = "Generator create random phone numbers")
public class PhoneNumberGenerator implements DataGenerator{

    @Override
    public List<String> getTestData() {
        return null;
    }

    @Override
    public String getGeneratorName() {
        return Optional.of(this.getClass().getAnnotation(GeneratorDescription.class).generatorName()).orElse("DEFAULT");
    }
}
