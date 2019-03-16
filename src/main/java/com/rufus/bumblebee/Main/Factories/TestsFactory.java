package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Columns.Columns;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringBoundaryValues;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringSpecialValues;
import com.rufus.bumblebee.Main.Generators.NumberGenerator.IntBoundaryValues;
import com.rufus.bumblebee.Main.Generators.NumberGenerator.IntFullRange;


public class TestsFactory {

    public Column getColumn(String name) {
        return new Column(name);
    }

    public StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, Column column) {
        return new StringBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, column);
    }

    public StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, Column column) {
        return new StringSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, column);
    }

    public IntBoundaryValues getIntBoundaryTest(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY, Columns column) {
        return new IntBoundaryValues(BoundaryIntEnd, BoundaryIntStart, QUANTITY, column);
    }

    public IntFullRange getIntFullRange(Long MaxIntVal, Long MinIntVal, Columns column) {
        return new IntFullRange(MaxIntVal, MinIntVal, column);
    }

}
