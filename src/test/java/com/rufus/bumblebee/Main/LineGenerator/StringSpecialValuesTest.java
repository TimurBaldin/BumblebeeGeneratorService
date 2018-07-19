package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringSpecialValuesTest {
    private final int Len = 13;
    private final String matchSpecial = "[^A-ZА-Яa-z]*";
    private StringSpecialValues test;
    private StringSpecialValues test1;
    private StringSpecialValues test2;
    private Column column;
    private final int MIN_ID_ESC = 1;
    private final int MAX_ID_ESC = 31;
    //Escape symbols
    private final int MIN_ID_SPECIAL_1 = 33;
    private final int MAX_ID_SPECIAL_1 = 64;
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2 = 128;
    private final int MAX_ID_SPECIAL_2 = 191;

    @Before
    public void precondition() {
        column = new Column("Test");
        test = new StringSpecialValues(12, 1, true, false, column);
        test1 = new StringSpecialValues(12, 1, true, true, column);
        test2 = new StringSpecialValues(13, 13, false, true, column);

    }

    @After
    public void delete() {
        column = null;
        test = null;
        test1 = null;
        test2 = null;
    }

    @Test
    public void testSizeWords() {
        try {
            test.construct();
            assertTrue(column.getValues().size() == Len);
            column.clear();
            test1.construct();
            assertTrue(column.getValues().size() == Len);
            column.clear();
            test2.construct();
            assertTrue(column.getValues().size() == Len * 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandomValue() {
        try {
            ArrayList<StringSpecialValues> tests = new ArrayList<StringSpecialValues>();
            tests.add(test);
            tests.add(test1);
            tests.add(test2);
            for (StringSpecialValues bufer : tests) {
                bufer.construct();
                List<TypeTestData> arrayList = column.getValues();
                String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
                assertTrue(buffer.matches(matchSpecial));
                column.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValueIDSpecial() {
        try {
            test2.construct();
            List<TypeTestData> arrayList = column.getValues();
            String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
            char [] testchars=buffer.toCharArray();
            for (char t:testchars) {
                boolean status=false;
                int result=t;
                if((result>=MIN_ID_SPECIAL_1 && result<= MAX_ID_SPECIAL_1)){
                    status=true;
                    }
                if(result>=MIN_ID_SPECIAL_2 && result<=MAX_ID_SPECIAL_2){
                    status=true;
                    }
                assertTrue(status);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testValueIDEsc() {
        try {
            test.construct();
            List<TypeTestData> arrayList = column.getValues();
            String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
            char [] testchars=buffer.toCharArray();
            for (char t:testchars) {
                boolean status=false;
                int result=t;
                if((result>=MIN_ID_ESC && result<= MAX_ID_ESC)){
                    status=true;
                }

                assertTrue(status);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValueIDEscSpecial() {
        try {
            test1.construct();
            List<TypeTestData> arrayList = column.getValues();
            String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
            char [] testchars=buffer.toCharArray();
            for (char t:testchars) {
                boolean status=false;
                int result=t;
                if((result>=MIN_ID_SPECIAL_1 && result<= MAX_ID_SPECIAL_1)){
                    status=true;
                }
                if(result>=MIN_ID_SPECIAL_2 && result<=MAX_ID_SPECIAL_2){
                    status=true;
                }
                if((result>=MIN_ID_ESC && result<= MAX_ID_ESC)){
                    status=true;
                }

                assertTrue(status);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}