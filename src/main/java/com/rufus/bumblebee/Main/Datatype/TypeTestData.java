package com.rufus.bumblebee.Main.Datatype;
/**
 * Class : Базовый класс для реализации пользовательских типов данных
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface TypeTestData<Type, Key> {
    /**
     *  Идентификатор типа данных
     */
    Key getKey();

    /**
     *  Тестовое значение
     */
    Type getValue();
}
