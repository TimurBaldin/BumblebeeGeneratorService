package com.rufus.bumblebee.Main.Columns;

import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnLines implements Columns<TypeTestData,String> {
private String COLUMN_NAME;
private List<TypeTestData> values = new ArrayList<TypeTestData>();
private List<String> report= Collections.synchronizedList(new ArrayList());

public ColumnLines(String COLUMN_NAME) {
    this.COLUMN_NAME = COLUMN_NAME;
}
@Override
public void setValues(List<TypeTestData> values) {
    this.values.addAll(values);
}
public void setReport(List<String> report){
    this.report.addAll(report);
}
@Override
public List<TypeTestData> getValues() {
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
    report.clear();
}
public String getTestValue(int id){
    if(id>report.size()-1){
        return null;
    }

   return  report.get(id);
}
public int getSizeValue(){return this.report.size();}
private boolean checkRule() {
    if (COLUMN_NAME == null || COLUMN_NAME == "" || values.size() == 0) {
        return true;
    } else {
        return false;
    }
}
}
