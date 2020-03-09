package com.rufus.bumblebee.datatype;

/**
 * Class : Основные типы данных (числа/строки/даты)
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class BaseDataType implements TypeTestData<String, String> {

    private String buffer;
    private String key;

    public BaseDataType(String buffer, String key) {
        this.buffer = buffer;
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return buffer;
    }

}
