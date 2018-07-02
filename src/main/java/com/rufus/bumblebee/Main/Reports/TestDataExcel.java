package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataExcel implements ReportExcel<Columns> {
    private XSSFRow row;
    private XSSFCell cell;
    private XSSFSheet sheet;
    private XSSFWorkbook book;
    private String docname;
    private String sheetname;
    private List<Columns> bufer = new ArrayList<Columns>();
    private final String REPORT_FOLDER = "TestDataFolder\\";
    private final String FILE_FORMAT = ".xlsx";
    private String path;

    @Override
    public File create(String docname, String sheetname, List<Columns> bufer) throws Exception {
        this.docname = docname;
        this.sheetname = sheetname;
        this.bufer.addAll(bufer);
        if (check()) {
            throw new Exception("Invalid input");
        } else {
            File buferdir=new File(System.getProperty("java.io.tmpdir")+REPORT_FOLDER);
            buferdir.mkdir();
            path = new String(System.getProperty("java.io.tmpdir") + REPORT_FOLDER + docname + FILE_FORMAT);
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
                int id = 1;
                boolean flag = checksize(id);
                int value_id = 0;

                while (flag) {
                    row = sheet.createRow(id);
                    int i = 0;
                    for (Columns column : bufer) {
                        cell = row.createCell(i);
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(column.getTestValue(value_id));
                        ++i;
                    }

                    ++value_id;
                    ++id;
                    flag = checksize(id);
                }


                book.write(fileOut);
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bufer.clear();
                for (Columns column : bufer) {
                    column.clear();
                }
                return file;
            }
        }
    }

    private boolean check() {
        if (docname == null || sheetname == null || bufer == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checksize(int id) {
        for (Columns column : bufer) {
            if (id <= (column.getSizeValue())) {
                return true;
            }
        }
        return false;
    }
}
