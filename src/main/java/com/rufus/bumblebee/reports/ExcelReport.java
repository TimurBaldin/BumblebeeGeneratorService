package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.container.Container;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class : создание/удаление отчета формата xlsx
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class ExcelReport implements ReportExcel<Container> {

    private XSSFRow row;
    private XSSFCell cell;
    private XSSFSheet sheet;
    private XSSFWorkbook book;
    private String docName;
    private String sheetName;
    private List<Container> buffer = new ArrayList<>();


    @Override
    public byte[] create(String docName, String sheetName, List<Container> bufer) throws InvalidInputException {
        this.docName = docName;
        this.sheetName = sheetName;
        this.buffer.addAll(bufer);
        if (check()) {
            throw new InvalidInputException("Invalid input");
        } else {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                book = new XSSFWorkbook();
                sheet = book.createSheet(sheetName);
                row = sheet.createRow(0);
                for (int j = 0; j <= bufer.size() - 1; j++) {
                    cell = row.createCell(j);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(bufer.get(j).getContainerName());
                }
                int id = 1;
                boolean flag = checkSize(id);
                int value_id = 0;

                while (flag) {
                    row = sheet.createRow(id);
                    int i = 0;
                    for (Container column : bufer) {
                        cell = row.createCell(i);
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(column.getTestValue(value_id));
                        ++i;
                    }

                    ++value_id;
                    ++id;
                    flag = checkSize(id);
                }


                book.write(baos);
               return baos.toByteArray();
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

    private boolean checkSize(int id) {
        for (Container column : buffer) {
            if (id <= (column.getSizeValue())) {
                return true;
            }
        }
        return false;
    }



}
