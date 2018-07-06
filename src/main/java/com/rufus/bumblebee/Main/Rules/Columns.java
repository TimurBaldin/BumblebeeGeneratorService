package com.rufus.bumblebee.Main.Rules;

import java.util.List;

public interface Columns<T,S> {
    void setValues(List<T> values);
    List<T> getValues();
    String getCOLUMN();
    int getSizeValue();
    void clear();
    String getTestValue(int id);
    void setReport(List<String> report);

}
