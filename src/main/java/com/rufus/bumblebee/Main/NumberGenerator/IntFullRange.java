package com.rufus.bumblebee.Main.NumberGenerator;

import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.Rules;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntFullRange implements Rules {
    private final String TYPE = "NUMERIC";
    private Long MinIntVal;
    private Long MaxIntVal;
    private List<TypeTestData> values = new LinkedList<TypeTestData>();
    private Columns column;

    public IntFullRange(Long MaxIntVal, Long MinIntVal, Columns column) {
        this.MinIntVal = MinIntVal;
        this.MaxIntVal = MaxIntVal;
        this.column = column;
    }

    @Override
    public void construct() throws Exception {
        if (checkRule()) {
            throw new Exception("Your choice is not right. Try again");
        }else {
            for (Long i = MinIntVal; i <= MaxIntVal; i++) {
                values.add(new BaseDatatype(buildRandNum().toString(), TYPE));
            }
            transfer();
        }
    }

    @Override
    public void transfer() throws Exception {
        if (column == null || values.size()==0) {
            throw new Exception("Value column not be null  or test data was not generated");
        } else {
            column.setValues(values);
        }
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