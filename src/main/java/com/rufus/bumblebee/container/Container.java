package com.rufus.bumblebee.container;

import com.rufus.bumblebee.generators.Rule;
import com.rufus.bumblebee.repository.TestDataRepository;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.List;

/**
 * Class : Базовый класс хранящий тестовые данные
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface Container<T, S> {
    /**
     * @return метод возвращает тестовые данные для записи в БД
     * @see TestDataRepository#create(List, String)
     */
    List<T> getValues() throws InvalidInputException;

    /**
     * Метод записывает тестовые данные от "генераторов"
     *
     * @see Rule#transfer()
     */
    void setValues(List<T> values);

    /**
     * @return метод возвращает название колонки для записи в БД
     * @see TestDataRepository#create(List, String)
     */
    String getContainerName();

    /**
     * @return метод возвращает количество тестовых данных
     */
    int getSizeValue();

    /**
     * Метод очищает тестовые данные
     */
    void clear();

    /**
     * Метод очищает данные используемые для создания отчета
     */
    void cleanReportData();

    /**
     * @param id идентификатор записи
     * @return возвращает тестовые данные по ID
     */
    String getTestValue(int id);

    /**
     * Передача тестовых данных для создание отчета
     *
     * @param report тестовые данные для записи в отчет
     * @see TestDataRepository#get(List)
     */
    void setReport(List<String> report);

}
