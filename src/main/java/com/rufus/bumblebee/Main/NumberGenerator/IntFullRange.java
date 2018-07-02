package com.rufus.bumblebee.Main.NumberGenerator;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.Rules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntFullRange implements Rules {
private Long MinIntVal;
private Long MaxIntVal;
private List<Number> values = new LinkedList<Number>();
private Columns column;
public IntFullRange(Long MaxIntVal, Long MinIntVal,Columns column) {
    this.MinIntVal = MinIntVal;
    this.MaxIntVal = MaxIntVal;
    this.column=column;
}

@Override
public void construct() throws Exception {
    if (checkRule()) {
        throw new Exception("Your choice is not right. Try again");
    }
    for (int i = 1; i <= MaxIntVal; i++) {
        values.add(buildRandNum());
    }
    transfer();
}

@Override
public void transfer() throws Exception {
    if(column==null){
        throw new Exception("Value column not be null");
    }else{ column.setValues(values);}
}

private boolean checkRule() {
    if ((MaxIntVal < MinIntVal) || (Math.abs(MaxIntVal - MinIntVal) == 0)) {
        return true;
    } else {
        return false;
    }
}

private Long buildRandNum() {
    return ThreadLocalRandom.current().nextLong(MinIntVal, MaxIntVal + 1);
}

}