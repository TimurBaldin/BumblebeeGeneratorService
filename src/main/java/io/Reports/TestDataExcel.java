package io.Reports;

import io.Columns.ColumnLines;
import io.Rules.Report.ReportExcel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataExcel implements ReportExcel<ColumnLines> {
    String DOC_NAME;
    String Sheet_NAME;
    private XSSFRow row;
    private XSSFCell cell;
    private XSSFSheet sheet;
    private XSSFWorkbook book;
    private List<ColumnLines> bufer = new ArrayList<ColumnLines>();

    @Override
    public void create(String DOC_NAME, String Sheet_NAME, List<ColumnLines> bufer) throws Exception {
        this.DOC_NAME = DOC_NAME;
        this.Sheet_NAME = Sheet_NAME;
        this.bufer.addAll(bufer);
        if (check()) {
            throw new Exception("Invalid input");
        } else {
            try {
                File file = new File("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\java\\io.Reports\\DOC\\" + DOC_NAME + ".xlsx");
                FileOutputStream fileOut = new FileOutputStream(file);
                file.createNewFile();
                preparation();
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
                    for (ColumnLines column: bufer) {
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
            }
        }
    }

    private void preparation() {
        if (book == null) {
            book = new XSSFWorkbook();
            sheet = book.createSheet(Sheet_NAME);
        }
    }

    private boolean check() {
        if (DOC_NAME == null || Sheet_NAME == null || bufer == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checksize(int id) {

        for (ColumnLines column: bufer) {
            if (id <= (column.getSizeValue())) {
                return true;
            }
        }
        return false;
    }
}
