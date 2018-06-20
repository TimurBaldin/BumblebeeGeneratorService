package com.rufus.bumblebee.Main.Rules;

import java.util.List;

public interface Columns<T> {
    void setValues(List<T> values);
    List<T> getValues();
    String getCOLUMN();
}
