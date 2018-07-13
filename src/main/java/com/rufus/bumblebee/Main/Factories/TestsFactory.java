package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.LineGenerator.StringBoundaryValues;
import com.rufus.bumblebee.Main.LineGenerator.StringSpecialValues;
import com.rufus.bumblebee.Main.NumberGenerator.IntBoundaryValues;
import com.rufus.bumblebee.Main.NumberGenerator.IntFullRange;
import com.rufus.bumblebee.Main.Rules.BaseFactory;
import com.rufus.bumblebee.Main.Rules.Columns;


public class TestsFactory implements BaseFactory {

    public ColumnLines getColumn(String name) {
        return new ColumnLines(name);
    }

    public StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, ColumnLines column) {
        return new StringBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, column);
    }

    public StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column) {
        return new StringSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, column);
    }

    public IntBoundaryValues getIntBoundaryTest(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY, Columns column) {
        return new IntBoundaryValues(BoundaryIntEnd, BoundaryIntStart, QUANTITY, column);
    }

    public IntFullRange getIntFullRange(Long MaxIntVal, Long MinIntVal, Columns column) {
        return new IntFullRange(MaxIntVal, MinIntVal, column);
    }
}
