package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
    private String docName;
    private String sheetName;
    private Map<String, List<TestData>> buffer = new HashMap<>();


    @Override
    public byte[] create(String docName, String sheetName, Map<String, List<TestData>> data) throws InvalidInputException {
        this.docName = docName;
        this.sheetName = sheetName;
        this.buffer.putAll(data);
        if (check()) {
            throw new InvalidInputException("Invalid input");
        } else {
            try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                book = new XSSFWorkbook();
                sheet = book.createSheet(sheetName);
                row = sheet.createRow(0);
                int cellId = 0;
                for (String key : buffer.keySet()) {
                    cell = row.createCell(cellId);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(key);
                    cellId++;
                }
                int id = 1;
                for (int j = 0; j <= buffer.size() - 1; j++) {
                    row = sheet.createRow(id);
                    List<TestData> dataList = buffer.get(j);
                    int i = 0;
                    for (TestData testData : dataList) {
                        cell = row.createCell(i);
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(testData.getValue());
                        ++i;
                    }
                    ++id;
                }

                book.write(stream);
                return stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean check() {
        if (docName == null || sheetName == null || buffer == null) {
            return true;
        } else {
            return false;
        }
    }

}
