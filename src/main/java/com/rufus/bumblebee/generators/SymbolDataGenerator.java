package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.generators.utils.ConvertorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static com.rufus.bumblebee.generators.SymbolDataGenerator.DataMode.NUMBER;
import static com.rufus.bumblebee.generators.SymbolDataGenerator.DataMode.STRING;

@GeneratorDescription(
        generatorName = "SymbolGenerator",
        generatorClass = SymbolDataGenerator.class,
        description = "Generator for create random values")
public class SymbolDataGenerator implements DataGenerator {

    @GeneratorParameter(
            name = "len",
            description = "The length of the text value, applied if isCascade = false",
            setValueMethod = "setLen"
    )
    private int len;

    @GeneratorParameter(
            name = "count",
            description = "Number of text values in the list",
            setValueMethod = "setCount")
    private int count;

    @GeneratorParameter(
            name = "mode",
            description = "Maybe value STRING or NUMBER",
            setValueMethod = "setDataMode")
    private DataMode dataMode;

    @GeneratorParameter(
            name = "isNull",
            description = "The presence of a NULL value",
            setValueMethod = "setIsNull")
    private boolean isNull;

    @GeneratorParameter(
            name = "isCascade",
            description = "Cascading increment of values in a text expression",
            setValueMethod = "setIsCascade")
    private boolean isCascade;

    private static final int MIN_ID_STRING = 1;
    private static final int MAX_ID_STRING = 192;
    private static final int MIN_ID_NUMERIC = 48;
    private static final int MAX_ID_NUMERIC = 58;

    @Override
    public List<String> getTestData() {
        List<String> values = new ArrayList<>(count + 1);
        if (dataMode.equals(STRING)) {
            generateData(MIN_ID_STRING, MAX_ID_STRING, values);
        }

        if (dataMode.equals(NUMBER)) {
            generateData(MIN_ID_NUMERIC, MAX_ID_NUMERIC, values);
        }
        return values;
    }

    @Override
    public String getGeneratorName() {
        return Optional.of(
                this.getClass().getAnnotation(GeneratorDescription.class).generatorName()
        ).orElse("DEFAULT");
    }

    private void generateData(int startSeq, int endSeq, List<String> values) {
        StringBuilder buffer = new StringBuilder();
        if (isNull) {
            String nullValue = null;
            buffer.append(nullValue);
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
            values.add(buffer.toString());
            buffer.delete(0, i);
        }
    }

    enum DataMode {
        STRING("STRING"),
        NUMBER("NUMBER");
        final String key;

        DataMode(String key) {
            this.key = key;
        }
    }

    public void setLen(String len) {
        this.len = ConvertorUtils.convertStringToInt(len);
    }

    public void setCount(String count) {
        this.count = ConvertorUtils.convertStringToInt(count);
    }

    public void setDataMode(String dataMode) {
        this.dataMode = DataMode.valueOf(dataMode.toUpperCase());
    }

    public void setNull(String isNull) {
        this.isNull = ConvertorUtils.convertStringToBoolean(isNull);
    }

    public void setCascade(String cascade) {
        isCascade = ConvertorUtils.convertStringToBoolean(cascade);
    }
}
