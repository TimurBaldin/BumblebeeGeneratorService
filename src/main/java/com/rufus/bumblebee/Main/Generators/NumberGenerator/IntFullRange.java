package com.rufus.bumblebee.Main.Generators.NumberGenerator;

import com.rufus.bumblebee.Main.Columns.Columns;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Generators.Rule;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует случайные числа в указанном диапазоне
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class IntFullRange implements Rule {

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
    public void construct() throws GeneratorExceptionInputOptions, TransferException {
        if (checkRule()) {
            throw new GeneratorExceptionInputOptions("Your choice is not right. Parameters :", MaxIntVal.toString() + MinIntVal.toString());
        } else {
            for (Long i = MinIntVal; i <= MaxIntVal; i++) {
                values.add(new BaseDatatype(buildRandNum().toString(), TYPE));
            }
            transfer();
        }
    }

    @Override
    public void transfer() throws TransferException {
        if (column == null || values.size() == 0) {
            throw new TransferException("Value column not be null  or test data was not generated ");
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