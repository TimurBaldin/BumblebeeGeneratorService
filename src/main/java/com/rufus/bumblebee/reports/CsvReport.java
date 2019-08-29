package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class : класс создает/удаляет отчет csv
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Component
public class CsvReport implements ReportCSV<Container> {
    private Map<String, List<TestData>> buffer = new HashMap<>();
    private String delimiter;
    private String docName;

    @Override
    public byte[] create(String docName, String delimiter, Map<String, List<TestData>> data) throws InvalidInputException {
        this.buffer.putAll(data);
        this.docName = docName;
        this.delimiter = delimiter;
        if (check()) {
            throw new InvalidInputException("Invalid input");
        } else {
            try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
                for (String key : buffer.keySet()) {
                    stream.write((key + delimiter).getBytes());
                }
                stream.write(System.getProperty("line.separator").getBytes());
                for (int j = 0; j <= buffer.size() - 1; j++) {
                    int i = 0;
                    List<TestData> dataList = buffer.get(j);
                    for (TestData testData : dataList) {
                        stream.write((testData.getValue() + delimiter).getBytes());
                        i++;
                    }
                    stream.write(System.getProperty("line.separator").getBytes());
                }

                return stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private boolean check() {
        if (docName == null || delimiter == null || buffer == null) {
            return true;
        } else {
            return false;
        }
    }

}
