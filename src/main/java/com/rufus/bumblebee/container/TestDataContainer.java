package com.rufus.bumblebee.container;

import com.rufus.bumblebee.datatype.TypeTestData;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class : Kласс хранящий тестовые данные
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Component
public class TestDataContainer implements Container<TypeTestData, String> {

    private String containerName;
    private List<TypeTestData> values = new ArrayList<>();
    private List<String> report = new ArrayList<>();

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setReport(List<String> report) {
        this.report.addAll(report);
    }

    @Override
    public List<TypeTestData> getValues() throws InvalidInputException {
        if (checkRule()) {
            throw new InvalidInputException("Test data not be null or containerName not be null");
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
