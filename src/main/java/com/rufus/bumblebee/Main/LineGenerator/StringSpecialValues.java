package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Rules;
import com.rufus.bumblebee.Main.Rules.TypeTestData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringSpecialValues implements Rules {
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
    private ColumnLines column;
    private List<TypeTestData> values = new LinkedList<TypeTestData>();

    public StringSpecialValues(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL, ColumnLines column) {
        this.SPECIAL_LEN = SPECIAL_LEN;
        this.ESC_SPECIAL = ESC_SPECIAL;
        this.SPECIAL = SPECIAL;
        this.INCREASE_QUANTITY = INCREASE_QUANTITY;
        this.column = column;
    }

    @Override
    public void construct() throws Exception {
        if (checkIn()) throw new Exception("Your choice is not right. Try again");
        if (ESC_SPECIAL && SPECIAL) {
            stringEsc((SPECIAL_LEN + INCREASE_QUANTITY) / 2);
            transfer();

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

    @Override
    public void transfer() throws Exception {
        if (checkOut()) {
            throw new Exception("Please create test data");
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
        if ((!ESC_SPECIAL && !SPECIAL) || SPECIAL_LEN <= 0 || INCREASE_QUANTITY <= 0) {
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
