package com.rufus.bumblebee.Main.Columns;

import com.rufus.bumblebee.Main.Generators.Rule;

import java.util.List;

/**
 * Class : Базовый класс хранящий тестовые данные
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface Columns<T, S> {
    /**
     * @return метод возвращает тестовые данные для записи в БД
     * @see com.rufus.bumblebee.Main.Repository.RepositiryTestValues#create(List, String)
     */
    List<T> getValues();

    /**
     * Метод записывает тестовые данные от "генераторов"
     *
     * @see Rule#transfer()
     */
    void setValues(List<T> values);

    /**
     * @return метод возвращает название колонки для записи в БД
     * @see com.rufus.bumblebee.Main.Repository.RepositiryTestValues#create(List, String)
     */
    String getCOLUMN();

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
     * @see com.rufus.bumblebee.Main.Repository.RepositiryTestValues#get(List)
     */
    void setReport(List<String> report);

}
