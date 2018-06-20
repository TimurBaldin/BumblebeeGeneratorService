package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Report.ReportCSV;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestDataCSV implements ReportCSV<ColumnLines> {
private List<ColumnLines> bufer = new ArrayList<ColumnLines>();
private String delimiter;
private String docname;
private final String REPORT_PATH = "src\\main\\java\\com\\rufus\\bumblebee\\Main\\Reports\\DOC\\";
private final String FILE_FORMAT = ".csv";
private String path;
private File file;

@Override
public File create(String docname, String delimiter, List<ColumnLines> bufer) throws Exception {
    this.bufer.addAll(bufer);
    this.docname = docname;
    this.delimiter = delimiter;
    path = new File(REPORT_PATH + docname + FILE_FORMAT).getAbsolutePath();
    if (check()) {
        throw new Exception("Invalid input");
    } else {
        file = new File(path);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (int j = 0; j <= bufer.size() - 1; j++) {
                writer.write(bufer.get(j).getCOLUMN() + delimiter);
            }
            writer.write(System.getProperty("line.separator"));
            boolean flag = true;
            int id = 1;
            int value_id = 0;
            while (flag) {
                int i = 0;
                for (ColumnLines column : bufer) {
                    writer.write(column.getTestValue(value_id) + delimiter);
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

@Override
public boolean delete() throws FileNotFoundException {
    if (path == null) {
        throw new FileNotFoundException("PATH not be null");
    } else {
        return file.delete();
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

private boolean check() {
    if (docname == null || delimiter == null || bufer == null || path == null) {
        return true;
    } else {
        return false;
    }
}
}
