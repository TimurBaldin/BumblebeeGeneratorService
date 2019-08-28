package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;

import java.util.List;

/**
 * Class : Базовый класс для генерации тестовых данных
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface Rule {
    /**
     * Вызов методов "генераторов"
     *
     * @throws GeneratorExceptionInputOptions не корректные входные данные
     */
    void construct() throws GeneratorExceptionInputOptions, TransferException;

    /**
     * Передача значений для записи в БД
     *
     * @throws TransferException ошибка при передачи данных
     */
    List<TypeTestData> receivingTestData() throws TransferException;

    Long getContainerRef();
}
