package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.TestData;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.List;
import java.util.Map;

/**
 * Class : Базовый класс создание/удаления отчета csv
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public interface ReportCSV<T> {
    /**
     * @param docName   название отчета
     * @param delimiter разделитель значений
     * @param data     данные для создания отчета
     * @return Созданный файл отчета
     * @throws InvalidInputException параметры метода не могут быть null
     */
    byte[] create(String docName, String delimiter, Map<String, List<TestData>> data) throws InvalidInputException;

}
