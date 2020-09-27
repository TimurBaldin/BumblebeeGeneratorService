package com.rufus.bumblebee.controllers.responses;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class GeneratorDto {
    private String generatorName;
    private String generatorDescription;
    List<GeneratorParametersDto> parameters;

    public GeneratorDto(String generatorName, String generatorDescription, List<GeneratorParametersDto> parameters) {
        this.generatorName = generatorName;
        this.generatorDescription = generatorDescription;
        this.parameters = parameters;
    }

    @JsonGetter
    public String getGeneratorName() {
        return generatorName;
    }

    @JsonGetter
    public String getGeneratorDescription() {
        return generatorDescription;
    }

    @JsonGetter
    public List<GeneratorParametersDto> getParameters() {
        return parameters;
    }
}
