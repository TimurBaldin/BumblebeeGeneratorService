package com.rufus.bumblebee.Main.Columns;

import com.rufus.bumblebee.Main.Rules.Columns;

import java.util.ArrayList;
import java.util.List;

public class ColumnNum implements Columns<Number> {
    private String COLUMN_NAME;
    private ArrayList<Number> values = new ArrayList<Number>();

    public ColumnNum(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    @Override
    public void setValues(List<Number> values) {
        this.values.addAll(values);
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

    public String getCOLUMN() {
        return COLUMN_NAME;
    }
    public void clear(){
        values.clear();
    }
    public Number getTestValue(int id){
        if(id>values.size()-1){
            return null;
        }
        return values.get(id);
    }
    public int getSizeValue(){return values.size();}
    private boolean checkRule() {
        if (COLUMN_NAME == null || COLUMN_NAME == "" || values.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
