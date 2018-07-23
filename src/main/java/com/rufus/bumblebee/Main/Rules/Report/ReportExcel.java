package com.rufus.bumblebee.Main.Rules.Report;

import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/**
 * Class : Базовый класс создание/удаления отчета excel
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface ReportExcel<T> {
    /**
     * @return Созданный файл отчета
     * @param docname название отчета
     * @param sheetname название листа
     * @param bufer данные для создания отчета
     * @throws InvalidInputException параметры метода не могут быть null
     */
    File create(String docname, String sheetname, List<T> bufer) throws InvalidInputException;
    /**
     * @return true при успешном удалении /false при ошибки
     * @throws FileNotFoundException файл не был найден
     */
    boolean delete() throws FileNotFoundException;
}
