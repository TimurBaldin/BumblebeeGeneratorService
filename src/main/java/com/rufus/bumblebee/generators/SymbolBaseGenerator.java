package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.generators.configurer.StringNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.rufus.bumblebee.generators.SymbolBaseGenerator.DataMode.NUMBER;
import static com.rufus.bumblebee.generators.SymbolBaseGenerator.DataMode.STRING;
import static com.rufus.bumblebee.generators.configurer.SpecialID.KEY;

@GeneratorDescription(
        generatorName = "SymbolGenerator",
        generatorClass = SymbolBaseGenerator.class,
        description = "Generator for create random strings"
)
@Component
@Scope("prototype")
public class SymbolBaseGenerator implements BaseGenerator {

    private List<TypeTestData> values = new LinkedList<TypeTestData>();

    @GeneratorParameter(name = "len", InClass = Integer.class)
    public Integer len;
    @GeneratorParameter(name = "count", InClass = Integer.class)
    public Integer count;
    @GeneratorParameter(name = "mode", description = "Maybe value STRING or NUMBER", InClass = String.class)
    public String mode;
    @GeneratorParameter(name = "isNull", InClass = Boolean.class)
    public Boolean isNull;
    @GeneratorParameter(name = "isCascade", InClass = Boolean.class)
    public Boolean isCascade;

    @Override
    public void construct() {
        if (DataMode.valueOf(mode).equals(STRING)) {
            generateData(KEY.MIN_ID_STRING, KEY.MAX_ID_STRING);
        }

        if (DataMode.valueOf(mode).equals(NUMBER)) {
            generateData(KEY.MIN_ID_NUMERIC, KEY.MAX_ID_NUMERIC);
        }
    }

    @Override
    public List<TypeTestData> getTestData() {
        return values;
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
            values.add(new BaseDataType(buffer.toString(), DataMode.valueOf(mode).getMode()));
            buffer.delete(0, i);
        }
    }

    enum DataMode {

        STRING("STRING"),
        NUMBER("NUMBER");
        String key;

        DataMode(String key) {
            this.key = key;
        }

        public String getMode() {
            return key;
        }
    }
}
