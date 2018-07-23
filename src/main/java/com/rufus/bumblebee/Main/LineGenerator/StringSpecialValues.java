package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Rules.Rule;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Class : Генерирует строки из специальных символов длиной от 1 до SPECIAL_LEN+INCREASE_QUANTITY
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public class StringSpecialValues implements Rule {
    private final int MIN_ID_ESC = 1;
    private final int MAX_ID_ESC = 31;
    //Escape symbols
    private final int MIN_ID_SPECIAL_1 = 33;
    private final int MAX_ID_SPECIAL_1 = 64;
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2 = 128;
    private final int MAX_ID_SPECIAL_2 = 191;
    private final String TYPE = "STRING";
    //Other symbols 2
    private Integer SPECIAL_LEN;
    private Boolean ESC_SPECIAL;
    private Boolean SPECIAL;
    private Integer INCREASE_QUANTITY;
    private Column column;
    private List<TypeTestData> values = new ArrayList<TypeTestData>();

    public StringSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, Column column) {
        this.SPECIAL_LEN = SPECIAL_LEN;
        this.ESC_SPECIAL = ESC_SPECIAL;
        this.SPECIAL = SPECIAL;
        this.INCREASE_QUANTITY = INCREASE_QUANTITY;
        this.column = column;
    }

    @Override
    public void construct() throws GeneratorExceptionInputOptions,TransferException {
        if (checkIn()) {
            throw new GeneratorExceptionInputOptions("Your choice is not right.Parameters : ",SPECIAL_LEN.toString()+ESC_SPECIAL.toString()+SPECIAL.toString()+INCREASE_QUANTITY.toString());
        }else {
            if (ESC_SPECIAL && SPECIAL) {
                if ((SPECIAL_LEN + INCREASE_QUANTITY) % 2 == 0) {
                    stringEsc((SPECIAL_LEN + INCREASE_QUANTITY) / 2);
                    stringSpecial((SPECIAL_LEN + INCREASE_QUANTITY) / 2);
                    transfer();
                } else {
                    stringEsc((SPECIAL_LEN + INCREASE_QUANTITY) / 2);
                    stringSpecial(((SPECIAL_LEN + INCREASE_QUANTITY) / 2) + 1);
                    transfer();
                }

            } else {
                if (SPECIAL) {
                    stringSpecial(SPECIAL_LEN + INCREASE_QUANTITY);
                    transfer();
                } else {
                    stringEsc(SPECIAL_LEN + INCREASE_QUANTITY);
                    transfer();
                }
            }
        }
    }

    @Override
    public void transfer() throws TransferException {
        if (checkOut()) {
            throw new TransferException("Please create test data");
        } else {
            column.setValues(this.values);
        }
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
            values.add(new BaseDatatype(bufer.toString(), TYPE));
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
            values.add(new BaseDatatype(bufer.toString(), TYPE));
            bufer.delete(0, i);
        }

        return bufer.toString();
    }

    private boolean checkIn() {
        if ((!ESC_SPECIAL && !SPECIAL) || SPECIAL_LEN <= 0 || INCREASE_QUANTITY <0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOut() {
        if ((values.size() == 0) || (column == null)) {
            return true;
        } else {
            return false;
        }
    }
}
