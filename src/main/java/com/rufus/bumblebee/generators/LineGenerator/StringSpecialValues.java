package com.rufus.bumblebee.generators.LineGenerator;

import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.BaseGenerator;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class : Генерирует строки из специальных символов длиной от 1 до specialLen+increaseQuantity
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class StringSpecialValues implements BaseGenerator {

    private final int MIN_ID_ESC = SpecialID.KEY_ID.getMIN_ID_ESC();
    private final int MAX_ID_ESC = SpecialID.KEY_ID.getMAX_ID_ESC();
    //Escape symbols
    private final int MIN_ID_SPECIAL_1 = SpecialID.KEY_ID.getMIN_ID_SPECIAL_1();
    private final int MAX_ID_SPECIAL_1 = SpecialID.KEY_ID.getMAX_ID_SPECIAL_1();
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2 = SpecialID.KEY_ID.getMIN_ID_SPECIAL_2();
    private final int MAX_ID_SPECIAL_2 = SpecialID.KEY_ID.getMAX_ID_SPECIAL_2();
    private final String TYPE = "STRING";
    //Other symbols 2
    private Integer specialLen;
    private Boolean escSpecial;
    private Boolean special;
    private Integer increaseQuantity;
    private Long containerRef;
    private List<TypeTestData> values = new LinkedList<>();

    @Builder(toBuilder = true)
    public StringSpecialValues(Integer specialLen, Integer increaseQuantity, Boolean escSpecial, Boolean special, Long containerRef) {
        this.specialLen = specialLen;
        this.escSpecial = escSpecial;
        this.special = special;
        this.increaseQuantity = increaseQuantity;
        this.containerRef = containerRef;
    }

    @Override
    public StringSpecialValues construct() throws GeneratorExceptionInputOptions, TransferException {
        if (checkIn()) {
            throw new GeneratorExceptionInputOptions("Your choice is not right.Parameters : ", specialLen.toString() + escSpecial.toString() + special.toString() + increaseQuantity.toString());
        } else {
            if (escSpecial && special) {
                if ((specialLen + increaseQuantity) % 2 == 0) {
                    stringEsc((specialLen + increaseQuantity) / 2);
                    stringSpecial((specialLen + increaseQuantity) / 2);
                } else {
                    stringEsc((specialLen + increaseQuantity) / 2);
                    stringSpecial(((specialLen + increaseQuantity) / 2) + 1);
                }
            } else {
                if (special) {
                    stringSpecial(specialLen + increaseQuantity);

                } else {
                    stringEsc(specialLen + increaseQuantity);

                }
            }
        }
        return this;
    }

    @Override
    public List<TypeTestData> receivingTestData() throws TransferException {
        if (CollectionUtils.isEmpty(values)) {
            throw new TransferException("Collection is empty for generator : "+getClass().getCanonicalName());
        }
        return values;
    }

    @Override
    public Long getContainerRef() {
        return containerRef;
    }

    private void stringEsc(int size) {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_ESC, MAX_ID_ESC + 1);
                char symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDataType(bufer.toString(), TYPE));
            bufer.delete(0, i);
        }

    }

    private String stringSpecial(int size) {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer i = 1; i <= size; i++) {
            for (Integer j = 1; j <= i; j++) {
                if (j % 2 == 0) {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_SPECIAL_1, MAX_ID_SPECIAL_1 + 1);
                } else {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_SPECIAL_2, MAX_ID_SPECIAL_2 + 1);
                }
                char symbol = (char) id;
                id = -1;
                bufer.append(symbol);
            }
            values.add(new BaseDataType(bufer.toString(), TYPE));
            bufer.delete(0, i);
        }

        return bufer.toString();
    }

    private boolean checkIn() {
        if ((!escSpecial && !special) || specialLen <= 0 || increaseQuantity < 0) {
            return true;
        } else {
            return false;
        }
    }

}
