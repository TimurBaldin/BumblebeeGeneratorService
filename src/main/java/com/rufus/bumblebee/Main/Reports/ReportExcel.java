package com.rufus.bumblebee.Main.Reports;

import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.FileNotFoundException;
import java.util.List;
/**
 * Class : Базовый класс создание/удаления отчета excel
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public interface ReportExcel<T> {
    /**
     * @return Созданный файл отчета
     * @param docName название отчета
     * @param sheetName название листа
     * @param bufer данные для создания отчета
     * @throws InvalidInputException параметры метода не могут быть null
     */
    byte[] create(String docName, String sheetName, List<T> bufer) throws InvalidInputException;

}
