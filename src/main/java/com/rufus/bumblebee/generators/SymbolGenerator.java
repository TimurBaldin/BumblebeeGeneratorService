package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.configurer.BaseGenerator;
import com.rufus.bumblebee.generators.configurer.DataMode;
import com.rufus.bumblebee.generators.configurer.StringNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.rufus.bumblebee.generators.configurer.DataMode.NUMBER;
import static com.rufus.bumblebee.generators.configurer.DataMode.STRING;
import static com.rufus.bumblebee.generators.configurer.SpecialID.KEY;

public class SymbolGenerator implements BaseGenerator {

    private List<TypeTestData> values = new LinkedList<>();
    private int len;
    private int count;
    private DataMode mode;
    private boolean isNull;
    private boolean isCascade;
    private long containerRef;

    public SymbolGenerator(int len, int count, DataMode mode, boolean isNull, boolean isCascade, long containerRef) {
        this.len = len;
        this.count = count;
        this.mode = mode;
        this.isNull = isNull;
        this.containerRef = containerRef;
        this.isCascade = isCascade;
    }

    @Override
    public void construct() {
        if (mode.getMode().equals(STRING.getMode())) {
            generateData(KEY.MIN_ID_STRING, KEY.MAX_ID_STRING);
        }

        if (mode.getMode().equals(NUMBER.getMode())) {
            generateData(KEY.MIN_ID_NUMERIC, KEY.MAX_ID_NUMERIC);
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

    private void generateData(int startSeq, int endSeq) {
        StringBuilder buffer = new StringBuilder();
        if (isNull) {
            buffer.append(StringNull.returnValue());
        }
        for (int i = 1; i <= count; i++) {
            if (isCascade) {
                for (int j = 1; j <= i; j++) {
                    buffer.append((char) ThreadLocalRandom.current().nextInt(startSeq, endSeq));
                }
            } else {
                for (int j = 1; j <= len; j++) {
                    buffer.append((char) ThreadLocalRandom.current().nextInt(startSeq, endSeq));
                }
            }
            values.add(new BaseDataType(buffer.toString(), mode.getMode()));
            buffer.delete(0, i);
        }
    }

}
