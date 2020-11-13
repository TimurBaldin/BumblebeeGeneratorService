package com.rufus.bumblebee.datatype;

import java.io.Serializable;

/**
 * Class : Базовый класс для реализации пользовательских типов данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface TypeTestData<Type, DataTypeId> extends Serializable {

    /**
     * Идентификатор типа данных
     */
    DataTypeId getDataType();

    /**
     * Тестовое значение
     */
    Type getValue();

}
