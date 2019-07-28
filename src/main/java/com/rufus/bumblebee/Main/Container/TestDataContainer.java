package com.rufus.bumblebee.Main.Container;

import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class : Kласс хранящий тестовые данные
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class TestDataContainer implements Container<TypeTestData, String> {

    private String containerName;
    private List<TypeTestData> values = new ArrayList<>();
    private List<String> report = new ArrayList<>();

    public TestDataContainer(String containerName) {
        this.containerName = containerName;
    }

    public void setReport(List<String> report) {
        this.report.addAll(report);
    }

    @Override
    public List<TypeTestData> getValues() {
        if (checkRule()) {
            try {
                throw new InvalidInputException("Test data not be null or containerName not be null");
            } catch (InvalidInputException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return values;
        }
    }

    @Override
    public void setValues(List<TypeTestData> values) {
        this.values.addAll(values);
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public void cleanReportData() {
        report.clear();
    }

    public String getTestValue(int id) {
        if (id > report.size() - 1) {
            return null;
        }
        return report.get(id);
    }

    public int getSizeValue() {
        return this.report.size();
    }

    private boolean checkRule() {
        return !StringUtils.isEmpty(containerName);
    }

}
