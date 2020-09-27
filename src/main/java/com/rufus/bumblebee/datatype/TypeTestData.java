package com.rufus.bumblebee.datatype;

import java.io.Serializable;

/**
 * Class : Базовый класс для реализации пользовательских типов данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface TypeTestData<Type, Key> extends Serializable {

    /**
     * Идентификатор типа данных
     */
    Key getKey();

    /**
     * Тестовое значение
     */
    Type getValue();

}
