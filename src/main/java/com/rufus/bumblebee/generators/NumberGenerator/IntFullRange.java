package com.rufus.bumblebee.generators.NumberGenerator;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует случайные числа в указанном диапазоне
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */

public class IntFullRange implements BaseGenerator {

    private final String TYPE = "NUMERIC";
    private Long minIntVal;
    private Long maxIntVal;
    private List<TypeTestData> values = new LinkedList<>();
    private Long containerRef;

    @Builder(toBuilder = true)
    public IntFullRange(Long maxIntVal, Long minIntVal, Long containerRef) {
        this.minIntVal = minIntVal;
        this.maxIntVal = maxIntVal;
        this.containerRef = containerRef;
    }

    @Override
    public IntFullRange construct() throws GeneratorExceptionInputOptions, TransferException {
        if (checkRule()) {
            throw new GeneratorExceptionInputOptions("Your choice is not right. Parameters :", maxIntVal.toString() + minIntVal.toString());
        } else {
            for (Long i = minIntVal; i <= maxIntVal; i++) {
                values.add(new BaseDataType(buildRandNum().toString(), TYPE));
            }
            receivingTestData();
        }
        return this;
    }

    @Override
    public List<TypeTestData> receivingTestData() throws TransferException {
        if (CollectionUtils.isEmpty(values)) {
            throw new TransferException("Collection is empty for generator : " + getClass().getCanonicalName());
        }
        return values;
    }

    @Override
    public Long getContainerRef() {
        return containerRef;
    }

    private boolean checkRule() {
        if ((maxIntVal < minIntVal) || (Math.abs(maxIntVal - minIntVal) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private Long buildRandNum() {
        return ThreadLocalRandom.current().nextLong(minIntVal, maxIntVal + 1);
    }

}