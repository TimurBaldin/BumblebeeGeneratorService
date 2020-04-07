package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class : создание/удаление отчета формата xlsx
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Component
public class ExcelReport implements ReportExcel<Container> {

    private XSSFRow row;
    private XSSFCell cell;
    private XSSFSheet sheet;
    private XSSFWorkbook book;

    @Override
    public byte[] create(String docName, String sheetName, Map<Container, List<TestData>> data) {

        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            book = new XSSFWorkbook();
            sheet = book.createSheet(sheetName);
            row = sheet.createRow(0);
            int cellId = 0;
            for (Container key : data.keySet()) {
                cell = row.createCell(cellId);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(key.getName());
                cellId++;
            }
            int id = 1;
            int countRow = 0;
            do {
                row = sheet.createRow(id);
                int i = 0;
                for (Container container : data.keySet()) {
                    List<TestData> dataList = container.getData();
                    String value = "";
                    cell = row.createCell(i);
                    cell.setCellType(CellType.STRING);
                    if (dataList.size() < countRow) {
                        cell.setCellValue(value);
                    } else {
                        value = dataList.get(countRow).getValue();
                        cell.setCellValue(value);
                    }
                    ++i;
                }
                countRow++;
                ++id;
            } while (
                    checkSizeContainers(data, countRow)
            );

            book.write(stream);
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private boolean checkSizeContainers(Map<Container, List<TestData>> data, int countRow) {
        for (Container container : data.keySet()) {
            if (container.getData().size() > countRow) {
                return true;
            }
        }
        return false;
    }


}
