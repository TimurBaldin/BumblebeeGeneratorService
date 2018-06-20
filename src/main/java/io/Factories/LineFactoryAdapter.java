package io.Factories;

import io.Columns.ColumnLines;
import io.LineGenerator.StringBoundaryValues;
import io.LineGenerator.StringSpecialValues;

public class LineFactoryAdapter extends LineFactory implements ILineFactory {

    @Override
    public ColumnLines getColumn(String name) {
        ColumnLines columnLines = super.getColumn(name);
        System.out.println(columnLines.hashCode());
        return  columnLines;
    }

    @Override
    public StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, ColumnLines column) {
        return super.getBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, column);
    }

    @Override
    public StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column) {
        return super.getSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, column);
    }
}
