package io.Columns;

import io.Rules.Columns;

import java.util.ArrayList;
import java.util.List;

public class ColumnNum implements Columns<Number> {
    private String COLUMN_NAME;
    private List values = new ArrayList<Number>();

    public ColumnNum(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    @Override
    public List<Number> getValues() {
        if (checkRule()) {
            try {
                throw new Exception("Test data not be null or COLUMN_NAME not be null");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return values;
        }
    }

    @Override
    public void setValues(List<Number> values) {
        this.values.addAll(values);
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
