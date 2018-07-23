package com.rufus.bumblebee.Main.Rules;

import java.util.List;

public interface Columns<T, S> {
    List<T> getValues();

    void setValues(List<T> values);

    String getCOLUMN();

    int getSizeValue();

    void clear();
    void cleanReportData();

    String getTestValue(int id);

    void setReport(List<String> report);

}
