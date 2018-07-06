package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Columns.*;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class StringBoundaryValuesTest {
    private   StringBoundaryValues test;
    private  StringBoundaryValues test1;
    private   StringBoundaryValues test2;
    private   ColumnLines column;
    private final int Len=14;
    private final int notNullLen=13;
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
            assertTrue(column.getValues().size()== Len);
            column.clear();
            test1.construct();
            assertTrue(column.getValues().size()== Len);
            column.clear();
            test2.construct();
            assertTrue(column.getValues().size()== notNullLen);
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
        List<TypeTestData> arrayList=column.getValues();
        String  buffer= (String) arrayList.get(arrayList.size()/2).getValue();
        assertTrue(buffer.matches(matchForLow));


    }
    @Test
    public void testWordValueCap(){
        try {
            test2.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList=column.getValues();
        String  buffer= (String) arrayList.get(arrayList.size()/2).getValue();
        assertTrue(buffer.matches(matchForCap));
    }
    @Test
    public void testWordValueCapLow(){
        try {
            test1.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList=column.getValues();
        String  buffer= (String) arrayList.get(arrayList.size()/2).getValue();
        boolean val1=buffer.matches(matchForLow);
        boolean val2=buffer.matches(matchForCap);
        Assert.assertEquals(val1,false);
        Assert.assertEquals(val2,false);

    }
    @After
    public void delete(){
         column=null;
         test=null;
         test1=null;
         test2=null;
    }
}