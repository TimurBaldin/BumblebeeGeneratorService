package com.rufus.bumblebee.Main.Datatype;

/**
 * Class : Основные типы данных (числа/строки/даты)
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class BaseDatatype implements TypeTestData<String, String> {

    private String bufer;
    private String key;

    public BaseDatatype(String bufer, String key) {
        this.bufer = bufer;
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return bufer;
    }

}
