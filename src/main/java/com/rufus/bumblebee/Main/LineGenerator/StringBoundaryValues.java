package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Rules;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringBoundaryValues implements Rules {
    private final int MIN_ID_CAPITALS = 65;
    private final int MAX_ID_CAPITALS = 90;
    private final int MIN_ID_LOWERCASE = 97;
    private final int MAX_ID_LOWERCASE = 122;
    private Integer len;
    private Boolean Low;
    private Boolean Cap;
    private Boolean NullValue;
    private Integer INCREASE_QUANTITY;
private ColumnLines column;
private List<TypeTestData> values = new LinkedList <TypeTestData>();
private final String  TYPE="STRING";

    public StringBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap,Boolean NullValue,ColumnLines column) {
        this.len = Len;
        this.Low = Low;
        this.Cap = Cap;
        this.INCREASE_QUANTITY = INCREASE_QUANTITY;
        this.NullValue=NullValue;
        this.column=column;
    }

    @Override
    public void construct() throws Exception {
        if (checkIn())
            throw new Exception("Your choice is not right. Try again");
        if(NullValue) {
            values.add(new BaseDatatype(new StringNull().returnValue(),TYPE));
            }
        if (Low && Cap) {
            stringBuildLowCap();
            transfer();
            return;
            } else  {
            if (Low && (!Cap)) {
                stringBuildLow();
                transfer();
            } else {
                stringBuildCap();
                transfer();
                }
        }
    }
    @Override
    public void transfer()throws Exception{
    if(checkOut()){ throw new Exception ("Please create test data");}
    else {
        column.setValues(this.values);
        this.values.clear();
        }
}

    private void stringBuildLowCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for(Integer j=1;j<=len + INCREASE_QUANTITY;j++) {
            for (Integer word = 1; word <= j; word++) {
                if (word % 2 == 0) {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                } else {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                }
                Character symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDatatype(bufer.toString(),TYPE));
            bufer.delete(0,j);
        }

    }

    private void stringBuildLow() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for(Integer j=1;j<=len + INCREASE_QUANTITY;j++) {

            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                Character symbol = (char) id;
                bufer.append(symbol);
                }
            values.add(new BaseDatatype(bufer.toString(),TYPE));
            bufer.delete(0,j);
        }

    }

    private void stringBuildCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for(Integer j=1;j<=len + INCREASE_QUANTITY;j++) {
            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                Character symbol = (char) id;
                bufer.append(symbol);

            }
            values.add(new BaseDatatype(bufer.toString(),TYPE));
            bufer.delete(0,j);
        }

        }
    private boolean checkIn(){
    if ((!Low && !Cap) || len <= 0 || INCREASE_QUANTITY <= 0){
        return true;
    } else {
        return false;
    }
}
    private boolean checkOut(){
    if((values.size()==0) ||(column==null)){
        return true;
    }else {return false;}
}
}
