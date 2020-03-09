package com.rufus.bumblebee.generators.NumberGenerator;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует числа для проверки "граничных значений"
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class IntBoundaryValues implements BaseGenerator {

    private final String TYPE = "NUMERIC";
    private Long maxInt;
    private Long minInt;
    private Integer quantity;
    private Long containerRef;
    private List<TypeTestData> values = new LinkedList<>();

    @Builder(toBuilder = false)
    public IntBoundaryValues(Long boundaryIntEnd, Long boundaryIntStart, Integer quantity, Long containerRef) {
        this.maxInt = boundaryIntEnd;
        this.minInt = boundaryIntStart;
        this.quantity = quantity;
        this.containerRef = containerRef;
    }

    @Override
    public IntBoundaryValues construct() throws GeneratorExceptionInputOptions {
        if (checkRule()) {
            throw new GeneratorExceptionInputOptions("Invalid input conditions.", maxInt.toString() + minInt.toString(), quantity.toString());
        } else {
            buildBoundary();
            buildTestNum();
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
        if ((maxInt < minInt) || (Math.abs(maxInt - minInt) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private Long buildRandNum() {
        Long Num_1;
        if (minInt >= 0) {
            Num_1 = minInt + 2;
        } else {
            Num_1 = minInt - 1;
        }
        return ThreadLocalRandom.current().nextLong(Num_1, maxInt);
    }

    private void buildBoundary() {
        for (Long j = -1L; j <= 1L; j++) {
            values.add(new BaseDataType(String.valueOf(minInt - j), TYPE));
            values.add(new BaseDataType(String.valueOf(maxInt - j), TYPE));

        }

    }

    private void buildTestNum() {
        for (int i = 1; i <= quantity; i++) {
            values.add(new BaseDataType(buildRandNum().toString(), TYPE));
        }
    }

}
