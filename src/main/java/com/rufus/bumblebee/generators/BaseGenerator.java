package com.rufus.bumblebee.generators;

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
     * Запуск генерации тестовых данных
     */
    List<TypeTestData> build();

}
