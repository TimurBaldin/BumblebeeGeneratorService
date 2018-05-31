package Columns;

import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Rules.*;
import Tools.*;

import java.util.ArrayList;
import java.util.List;

public class ColumnLines implements FactoryRules<String> {
private String COLUMN_NAME;
private List<String> values = new ArrayList<String>();

public ColumnLines(String COLUMN_NAME) {
    this.COLUMN_NAME = COLUMN_NAME;
}

@Override
public void setValues(List<String> values) {
    this.values.addAll(values);
}
@Override
public List<String> getValues() {
    if (checkRule()) {
        try {
            throw new Exception("Test data not be null or COLUMN_NAME not be null");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }else {
        return values;
    }
}

public String getCOLUMN() {
    return COLUMN_NAME;
}

private boolean checkRule() {
    if (COLUMN_NAME == null || COLUMN_NAME == "" || values.size() == 0) {
        return true;
    } else {
        return false;
    }
}
}
