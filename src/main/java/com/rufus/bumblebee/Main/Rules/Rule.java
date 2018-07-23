package com.rufus.bumblebee.Main.Rules;

import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
/**
 * Class : Базовый класс для генерации тестовых данных
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface Rule {
    /**
     *  Вызов методов "генераторов"
     *  @exception GeneratorExceptionInputOptions не корректные входные данные
     */
    void construct() throws GeneratorExceptionInputOptions,TransferException;
    /**
     *  Передача значений в объект "колонку"
     *  @exception  TransferException ошибка при передачи данных
     */
    void transfer() throws TransferException;
}
