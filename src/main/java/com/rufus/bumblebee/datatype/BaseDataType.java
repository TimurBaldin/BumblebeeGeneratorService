package com.rufus.bumblebee.datatype;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Class : Основные типы данных (числа/строки/даты)
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class BaseDataType implements TypeTestData<String, String> {

    private String value;
    private String dataType;

    public BaseDataType(String value, String dataType) {
        this.value = value;
        this.dataType = dataType;
    }

    @Override
    @JsonGetter
    public String getDataType() {
        return dataType;
    }

    @Override
    @JsonGetter
    public String getValue() {
        return value;
    }
}
