package com.rufus.bumblebee.reports;

import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    @Override
    public byte[] create(String docName, String delimiter, Map<Container, List<TestData>> data) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            for (Container container : data.keySet()) {
                stream.write((container.getName() + delimiter).getBytes());
            }
            stream.write(System.getProperty("line.separator").getBytes());
            int countRow = 0;
            do {
                for (Container container : data.keySet()) {
                    List<TestData> dataList = container.getData();
                    String value = "";
                    if (dataList.size() < countRow) {
                        stream.write((value.concat(delimiter)).getBytes());
                    } else {
                        value = dataList.get(countRow).getValue();
                        stream.write((value.concat(delimiter)).getBytes());
                    }
                }
                countRow++;
                stream.write(System.getProperty("line.separator").getBytes());
            } while (
                    checkSizeContainers(data, countRow)
            );

            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];

    }

    private boolean checkSizeContainers(Map<Container, List<TestData>> data, int countRow) {
        for (Container container : data.keySet()) {
            if (container.getData().size() > countRow) {
                return true;
            }
        }
        return false;
    }

}
