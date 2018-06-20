package com.rufus.bumblebee.Main.Columns;

import com.rufus.bumblebee.Main.Rules.Columns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnLines implements Columns<String> {
private String COLUMN_NAME;
private List<String> values = Collections.synchronizedList(new ArrayList());

public ColumnLines(String COLUMN_NAME) {
    this.COLUMN_NAME = COLUMN_NAME;
}
@Override
public void setValues(List<String> values) {
    this.values=values;
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
public void clear(){
    values.clear();
}
public String getTestValue(int id){
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
