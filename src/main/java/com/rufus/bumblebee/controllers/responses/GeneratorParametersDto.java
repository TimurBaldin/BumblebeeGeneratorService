package com.rufus.bumblebee.controllers.responses;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GeneratorParametersDto {
    private String parameter;
    private String parameterDescription;

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
