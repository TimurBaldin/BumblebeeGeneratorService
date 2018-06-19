package io.Factories;

import io.Columns.ColumnLines;
import io.LineGenerator.StringBoundaryValues;
import io.LineGenerator.StringSpecialValues;
import org.springframework.stereotype.Component;

@Component
public class LineFactory implements ILineFactory {

    public ColumnLines getColumn(String name) {
        return new ColumnLines(name);
    }

    public StringBoundaryValues getBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, ColumnLines column) {
        return new StringBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, column);
    }

    public StringSpecialValues getSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column) {
        return new StringSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, column);
    }

}
