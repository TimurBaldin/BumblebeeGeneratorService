package io.Factories;

import io.Columns.ColumnLines;
import io.LineGenerator.StringBoundaryValues;
import io.LineGenerator.StringSpecialValues;

public interface ILineFactory {

    ColumnLines getColumn(String name);

    StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, ColumnLines column);

    StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column);
}
