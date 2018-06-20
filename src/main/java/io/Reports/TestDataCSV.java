package io.Reports;

import io.Columns.ColumnLines;
import io.Rules.Report.ReportCSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataCSV implements ReportCSV<ColumnLines> {
    private List<ColumnLines> bufer = new ArrayList<ColumnLines>();
    private String delimetr;
    private String DOC_NAME;

    @Override
    public File create(String DOC_NAME, String delimetr, List<ColumnLines> bufer) throws Exception {
        this.bufer.addAll(bufer);
        this.DOC_NAME = DOC_NAME;
        this.delimetr = delimetr;
        if (check()) {
            throw new Exception("Invalid input");
        } else {
            File file = new File("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\java\\io.Reports\\DOC\\" + DOC_NAME + ".csv");
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                for (int j = 0; j <= bufer.size() - 1; j++) {
                    writer.write(bufer.get(j).getCOLUMN() + delimetr);
                }
                writer.write(System.getProperty("line.separator"));
                boolean flag = true;
                int id = 1;
                int value_id = 0;
                while (flag) {
                    int i = 0;
                    for (ColumnLines column: bufer) {
                        writer.write(column.getTestValue(value_id) + delimetr);
                        i++;
                    }
                    writer.write(System.getProperty("line.separator"));
                    value_id++;
                    id++;
                    flag = checksize(id);
                }
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return file;
            }
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

    private boolean check() {
        if (DOC_NAME == null || delimetr == null || bufer == null) {
            return true;
        } else {
            return false;
        }
    }
}
