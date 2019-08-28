package com.rufus.bumblebee.generators.LineGenerator;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.Rule;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует строки от 1 до len+increaseQuantity
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class StringBoundaryValues implements Rule {

    private final int MIN_ID_CAPITALS = 65;
    private final int MAX_ID_CAPITALS = 90;
    private final int MIN_ID_LOWERCASE = 97;
    private final int MAX_ID_LOWERCASE = 122;
    private final String TYPE = "STRING";
    private Integer len;
    private Boolean low;
    private Boolean cap;
    private Boolean nullValue;
    private Integer increaseQuantity;
    private Long containerRef;
    private List<TypeTestData> values = new ArrayList<TypeTestData>();

    @Builder(toBuilder = true)
    public StringBoundaryValues(Integer len, Integer increaseQuantity, Boolean low, Boolean cap, Boolean nullValue, Long containerRef) {
        this.len = len;
        this.low = low;
        this.cap = cap;
        this.increaseQuantity = increaseQuantity;
        this.nullValue = nullValue;
        this.containerRef = containerRef;
    }

    @Override
    public void construct() throws GeneratorExceptionInputOptions, TransferException {
        if (checkIn()) {
            String paremeters = len.toString() + low.toString() + cap.toString() + increaseQuantity.toString() + nullValue.toString();
            throw new GeneratorExceptionInputOptions("Your choice is not right.", paremeters);
        } else {
            if (nullValue) {
                values.add(new BaseDataType(new StringNull().returnValue(), TYPE));
            }
            if (low && cap) {
                stringBuildLowCap();
            } else {
                if (low && (!cap)) {
                    stringBuildLow();
                } else {
                    stringBuildCap();
                }
            }
        }
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

    private void stringBuildLowCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + increaseQuantity; j++) {
            for (Integer word = 1; word <= j; word++) {
                if (word % 2 == 0) {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                } else {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                }
                Character symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDataType(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private void stringBuildLow() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + increaseQuantity; j++) {

            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                Character symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDataType(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private void stringBuildCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + increaseQuantity; j++) {
            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                Character symbol = (char) id;
                bufer.append(symbol);

            }
            values.add(new BaseDataType(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private boolean checkIn() {
        if ((!low && !cap) || len <= 0 || increaseQuantity < 0) {
            return true;
        } else {
            return false;
        }
    }

}
