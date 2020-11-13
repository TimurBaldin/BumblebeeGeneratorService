package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;

import java.io.Serializable;
import java.util.List;

/**
 * Class : Базовый класс для генерации тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface BaseGenerator extends Serializable {

    /**
     * Вызов методов "генераторов"
     */
    void construct();

    /**
     * Передача значений для записи в БД
     *
     * @return
     */
    List<TypeTestData> getTestData();

}
