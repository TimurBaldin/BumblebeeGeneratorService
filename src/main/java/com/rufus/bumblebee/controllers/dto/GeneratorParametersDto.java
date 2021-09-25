package com.rufus.bumblebee.controllers.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GeneratorParametersDto {
    private final String parameter;
    private final String parameterDescription;

    public GeneratorParametersDto(String parameter, String parameterDescription) {
        this.parameter = parameter;
        this.parameterDescription = parameterDescription;
    }

    @JsonGetter
    public String getParameter() {
        return parameter;
    }

    @JsonGetter
    public String getParameterDescription() {
        return parameterDescription;
    }

}
