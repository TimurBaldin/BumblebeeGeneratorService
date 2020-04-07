package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.TestData;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.util.List;
import java.util.Map;

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
     * @param data данные для создания отчета
     */
    byte[] create(String docName, String sheetName, Map<T, List<TestData>> data);

}
