package com.rufus.bumblebee.Main.NumberGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IntBoundaryValuesTest {
    private final int Len = 8;
    private final int noQuantity = 6;
    private final String matchForInt = "[0-9]*";
    private IntBoundaryValues test;
    private IntBoundaryValues test1;
    private Column column;
    @Before
    public void precondition() {
        column = new Column("Test");
        test=new IntBoundaryValues(10L,1L,2,column);
        test1=new IntBoundaryValues(10L,1L,0,column);
    }
    @After
    public void delete(){
        column=null;
        test=null;
        test1=null;
    }
    @Test
    public void sizeValuesTest(){
        try {
            test.construct();
            assertTrue(column.getValues().size() == Len);
            column.clear();

            test1.construct();
            assertTrue(column.getValues().size() == noQuantity);
            column.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testRandomValue() {
        try {
            test1.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TypeTestData> arrayList = column.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        assertTrue(buffer.matches(matchForInt));
    }
    @Test(expected=Exception.class)
    public void exceptionCall() throws Exception {
        test.transfer();

    }
    @Test(expected=Exception.class)
    public void negativeInput() throws Exception {
        IntBoundaryValues  test2=new IntBoundaryValues(1L,100l,0,column);
        test2.construct();
        IntBoundaryValues  test3=new IntBoundaryValues(1L,1L,0,column);
        test2.construct();
    }
    @Test(expected=Exception.class)
    public void negativeInputColumn() throws Exception {
        IntBoundaryValues test2=new IntBoundaryValues(12L,1l,0,null);
        test2.construct();
    }
}