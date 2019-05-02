package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Columns.Columns;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class : класс создает/удаляет отчет csv
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public class TestDataCSV implements ReportCSV<Columns> {
    private final String REPORT_FOLDER = "TestDataFolder\\";
    private final String FILE_FORMAT = ".csv";
    private List<Columns> bufer = new ArrayList<Columns>();
    private String delimiter;
    private String docname;
    private String path;
    private File file;

    @Override
    public File create(String docname, String delimiter, List<Columns> bufer) throws InvalidInputException {
        this.bufer.addAll(bufer);
        this.docname = docname;
        this.delimiter = delimiter;
        if (check()) {
            throw new InvalidInputException("Invalid input");
        } else {
            File buferdir = new File(System.getProperty("java.io.tmpdir") + REPORT_FOLDER);
            buferdir.mkdir();
            path = new String(System.getProperty("java.io.tmpdir") + REPORT_FOLDER + docname + FILE_FORMAT);
            file = new File(path);
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                for (int j = 0; j <= bufer.size() - 1; j++) {
                    writer.write(bufer.get(j).getColumn() + delimiter);
                }
                writer.write(System.getProperty("line.separator"));
                boolean flag = true;
                int id = 1;
                int value_id = 0;
                while (flag) {
                    int i = 0;
                    for (Columns column : bufer) {
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
                for (Columns column : bufer) {
                    column.clear();
                }
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

        for (Columns column : bufer) {
            if (id <= (column.getSizeValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean check() {
        if (docname == null || delimiter == null || bufer == null) {
            return true;
        } else {
            return false;
        }
    }

}
