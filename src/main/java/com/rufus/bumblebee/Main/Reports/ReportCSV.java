package com.rufus.bumblebee.Main.Reports;

import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.List;

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
     * @param bufer     данные для создания отчета
     * @return Созданный файл отчета
     * @throws InvalidInputException параметры метода не могут быть null
     */
    byte[] create(String docName, String delimiter, List<T> bufer) throws InvalidInputException;

}
