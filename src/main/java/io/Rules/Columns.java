package io.Rules;

import java.util.List;

public interface Columns<T> {
    List<T> getValues();

    void setValues(List<T> values);

    String getCOLUMN();
}
