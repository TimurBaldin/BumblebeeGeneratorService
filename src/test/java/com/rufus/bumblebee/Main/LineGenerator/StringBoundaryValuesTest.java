package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class StringBoundaryValuesTest {
    private final int Len = 14;
    private final int notNullLen = 13;
    private final String matchForLow = "[a-zа-я]*";
    private final String matchForCap = "[A-ZА-Я]*";
    private StringBoundaryValues test;
    private StringBoundaryValues test1;
    private StringBoundaryValues test2;
    private Column column;

    @Before
    public void precondition() {
        column = new Column("Test");
        test = new StringBoundaryValues(12, 1, true, false, true, column);
        test1 = new StringBoundaryValues(12, 1, true, true, true, column);
        test2 = new StringBoundaryValues(12, 1, false, true, false, column);
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
            assertTrue(column.getValues().size() == notNullLen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandomValueLow() {
        try {
            test.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList = column.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        assertTrue(buffer.matches(matchForLow));


    }

    @Test
    public void testRandomValueCap() {
        try {
            test2.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList = column.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        assertTrue(buffer.matches(matchForCap));
    }

    @Test
    public void testRandomValueCapLow() {
        try {
            test1.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList = column.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        boolean val1 = buffer.matches(matchForLow);
        boolean val2 = buffer.matches(matchForCap);
        Assert.assertEquals(val1, false);
        Assert.assertEquals(val2, false);

    }

    @After
    public void delete() {
        column = null;
        test = null;
        test1 = null;
        test2 = null;
    }
}