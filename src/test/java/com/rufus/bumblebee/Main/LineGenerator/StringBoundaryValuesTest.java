package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class StringBoundaryValuesTest {
    private   StringBoundaryValues test;
    private  StringBoundaryValues test1;
    private   StringBoundaryValues test2;
    private   ColumnLines column;
    private int Len=14;
    private int notNullLen=13;
    private final String matchForLow="[a-zа-я]*";
    private final String matchForCap="[A-ZА-Я]*";
    @Before
    public void precondition() {
        column = new ColumnLines("Test");
        test = new StringBoundaryValues(12, 1, true, false, true, column);
        test1 = new StringBoundaryValues(12, 1, true, true, true, column);
        test2 = new StringBoundaryValues(12, 1, false, true, false, column);
    }

    @Test
    public void testSizeWords() {
        try {
            test.construct();
            assertTrue(column.getSizeValue()== Len);
            column.clear();
            test1.construct();
            assertTrue(column.getSizeValue()== Len);
            column.clear();
            test2.construct();
            assertTrue(column.getSizeValue()== notNullLen);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testWordValueLow(){
        try {
            test.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String  buffer=column.getTestValue(column.getSizeValue()/2);
        assertTrue(buffer.matches(matchForLow));


    }
    @Test
    public void testWordValueCap(){
        try {
            test2.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String  buffer=column.getTestValue(column.getSizeValue()/2);
        assertTrue(buffer.matches(matchForCap));
    }
    @Test
    public void testWordValueCapLow(){
        try {
            test1.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String  buffer=column.getTestValue(column.getSizeValue()/2);
        boolean val1=buffer.matches(matchForLow);
        boolean val2=buffer.matches(matchForCap);
        Assert.assertEquals(val1,false);
        Assert.assertEquals(val2,false);

    }
}