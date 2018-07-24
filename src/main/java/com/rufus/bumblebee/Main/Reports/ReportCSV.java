package com.rufus.bumblebee.Main.Reports;

import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
/**
 * Class : Базовый класс создание/удаления отчета csv
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface ReportCSV<T> {
    /**
     * @return Созданный файл отчета
     * @param docname название отчета
     * @param delimiter разделитель значений
     * @param bufer данные для создания отчета
     * @throws InvalidInputException параметры метода не могут быть null
     */
    File create(String docname, String delimiter, List<T> bufer) throws InvalidInputException;
    /**
     * @return true при успешном удалении /false при ошибки
     * @throws FileNotFoundException файл не был найден
     */
    boolean delete() throws FileNotFoundException;
}
