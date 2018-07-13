package com.rufus.bumblebee.Main.NumberGenerator;

import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.Rules;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntBoundaryValues implements Rules {
    private final String TYPE = "NUMERIC";
    private Long MaxInt;
    private Long MinInt;
    private Integer QUANTITY;
    private Columns column;
    private List<TypeTestData> values = new LinkedList<TypeTestData>();

    public IntBoundaryValues(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY, Columns column) {
        this.MaxInt = BoundaryIntEnd;
        this.MinInt = BoundaryIntStart;
        this.QUANTITY = QUANTITY;
        this.column = column;
    }

    @Override
    public void construct() throws Exception {
        if (checkRule()) {
            throw new Exception("Invalid input conditions");
        }
        buildBoundary();
        buildTestNum();
        transfer();
    }

    @Override
    public void transfer() throws Exception {
        if (column == null) {
            throw new Exception("Value column not be null");
        } else {
            column.setValues(values);
        }
    }

    private boolean checkRule() {
        if ((MaxInt < MinInt) || (Math.abs(MaxInt - MinInt) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private Long buildRandNum() {
        Long Num_1;
        if (MinInt >= 0) {
            Num_1 = MinInt + 2;
        } else {
            Num_1 = MinInt - 1;
        }
        return ThreadLocalRandom.current().nextLong(Num_1, MaxInt);
    }

    private void buildBoundary() {
        for (Long j = -1L; j <= 1L; j++) {
            values.add(new BaseDatatype(String.valueOf(MinInt - j), TYPE));
            values.add(new BaseDatatype(String.valueOf(MaxInt - j), TYPE));

        }

    }

    private void buildTestNum() {
        for (int i = 1; i <= Math.abs(MaxInt - MinInt) + QUANTITY; i++) {
            values.add(new BaseDatatype(buildRandNum().toString(), TYPE));
        }

    }
}
