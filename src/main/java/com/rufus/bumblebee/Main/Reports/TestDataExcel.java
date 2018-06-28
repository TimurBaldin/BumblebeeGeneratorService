package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataExcel implements ReportExcel<ColumnLines> {
private XSSFRow row;
private XSSFCell cell;
private XSSFSheet sheet;
private XSSFWorkbook book;
private String docname;
private String sheetname;
private List<ColumnLines> bufer = new ArrayList<ColumnLines>();
private final String REPORT_PATH="src\\main\\java\\com\\rufus\\bumblebee\\Main\\Reports\\DOC\\";
private final String FILE_FORMAT=".xlsx";
private String path;

@Override
public File create(String docname, String sheetname, List<ColumnLines> bufer) throws Exception {
    this.docname = docname;
    this.sheetname = sheetname;
    this.bufer.addAll(bufer);
    path = new File(REPORT_PATH + docname + FILE_FORMAT).getAbsolutePath();
    if (check()) {
        throw new Exception("Invalid input");
    } else {
        File file = new File(path);
        try {
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            book = new XSSFWorkbook();
            sheet = book.createSheet(sheetname);
            row = sheet.createRow(0);
            for (int j = 0; j <= bufer.size() - 1; j++) {
                cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(bufer.get(j).getCOLUMN());
            }
            boolean flag = true;
            int id = 1;
            int value_id = 0;
            while (flag) {
                row = sheet.createRow(id);
                int i = 0;
                for (ColumnLines column : bufer) {
                    cell = row.createCell(i);
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(column.getTestValue(value_id));
                    i++;
                }
                value_id++;
                id++;
                flag = checksize(id);
            }
            bufer.clear();
            book.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return file;
        }
    }
}

private boolean check() {
    if (docname == null || sheetname == null || bufer == null || path==null ) {
        return true;
    } else {
        return false;
    }
}

private boolean checksize(int id) {
    for (ColumnLines column : bufer) {
        if (id <= (column.getSizeValue())) {
            return true;
        }
    }
    return false;
}
}
