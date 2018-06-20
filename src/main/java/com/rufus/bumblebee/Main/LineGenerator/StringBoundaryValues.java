package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Rules;

import java.util.ArrayList;
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
private List<String> values = new ArrayList<String>();

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
            values.add(new StringNull().returnValue());
        }
        if (Low && Cap) {
            for (Integer i = 1; i <= len + INCREASE_QUANTITY; i++) {
                values.add(stringBuildLowCap(i));
            }
            transfer();
            return;
        }
        if (Low) {
            for (Integer i = 1; i <= len + INCREASE_QUANTITY; i++) {
                values.add(stringBuildLow(i));
            }
            transfer();
            return;
        } else {
            for (Integer i = 1; i <= len + INCREASE_QUANTITY; i++) {
                values.add(stringBuildCap(i));
            }
             transfer();
            return;
            }
    }
@Override
public void transfer()throws Exception{
    if(checkOut()){ throw new Exception ("Please create test data");}
    else {
        column.setValues(this.values);
    }
}

    private String stringBuildLowCap(int val) {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer i = 1; i <= val; i++) {
            if (i % 2 == 0) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
            } else {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
            }
            char symbol = (char) id;
            id = -1;
            bufer.append(symbol);
        }
        return bufer.toString();
    }

    private String stringBuildLow(int val) {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer i = 1; i <= val; i++) {
            id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
            char symbol = (char) id;
            id = -1;
            bufer.append(symbol);
        }
        return bufer.toString();
    }

    private String stringBuildCap(int val) {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer i = 1; i <= val; i++) {
            id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
            char symbol = (char) id;
            id = -1;
            bufer.append(symbol);

        }
        return bufer.toString();
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
