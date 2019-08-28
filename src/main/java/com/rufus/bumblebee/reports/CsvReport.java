package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.Container;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class : класс создает/удаляет отчет csv
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public abstract class CsvReport implements ReportCSV<Container> {
  /*  private List<Container> bufer = new ArrayList<>();
    private String delimiter;
    private String docName;
    private String path;

    @Override
    public byte[] create(String docName, String delimiter, List<Container> bufer) throws InvalidInputException {
        this.bufer.addAll(bufer);
        this.docName = docName;
        this.delimiter = delimiter;
        if (check()) {
            throw new InvalidInputException("Invalid input");
        } else {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                for (int j = 0; j <= bufer.size() - 1; j++) {
                    baos.write((bufer.get(j).getContainerName() + delimiter).getBytes());
                }
                baos.write(System.getProperty("line.separator").getBytes());
                boolean flag = true;
                int id = 1;
                int value_id = 0;
                while (flag) {
                    int i = 0;
                    for (Container column : bufer) {
                        baos.write((column.getTestValue(value_id) + delimiter).getBytes());
                        i++;
                    }
                    baos.write(System.getProperty("line.separator").getBytes());
                    value_id++;
                    id++;
                    flag = checkSize(id);
                }
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private boolean checkSize(int id) {
        for (Container column : bufer) {
            if (id <= (column.getSizeValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean check() {
        if (docName == null || delimiter == null || bufer == null) {
            return true;
        } else {
            return false;
        }
    }
*/
}
