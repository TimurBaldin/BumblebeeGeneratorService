package com.rufus.bumblebee.controllers.requests.tests;

import com.rufus.bumblebee.generators.configurer.DataMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class SymbolGeneratorRequest extends BaseRequest {

    @Min(value = 1,
            message = "Минимальное значение параметра len: 1")
    @Max(value = 1000000,
            message = "Максимальное значение параметра len: 1000000")
    private Integer len;

    @Min(value = 1,
            message = "Минимальное значение параметра count: 1")
    @Max(value = 10000000,
            message = "Максимальное значение параметра count: 10000000")
    private Integer count;

    private Boolean isNull;

    private Boolean isCascade;

    private Boolean nullValue;

    @NotNull(message = "DataMode не может быть null")
    private DataMode mode;

}
