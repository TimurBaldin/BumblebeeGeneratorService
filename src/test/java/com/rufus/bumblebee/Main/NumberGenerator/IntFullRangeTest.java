package com.rufus.bumblebee.Main.NumberGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IntFullRangeTest {
    private final int Len = 10;
    private final String matchForInt = "[0-9]*";
    private IntFullRange test;
    private Column column;
    @Before
    public void precondition() {
        column = new Column("Test");
        test=new IntFullRange(10L,1L,column);
    }
    @After
    public void delete(){
        column=null;
        test=null;
        }
    private void construct(IntFullRange bufer){
        try {
            bufer.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getValue(){
        List<TypeTestData> arrayList = column.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        return buffer;
    }
    @Test
    public void sizeValuesTest(){
        try {
            construct(test);
            assertTrue(column.getValues().size() == Len);
            column.clear();
            } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testRandomValue() {
        try {
            construct(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(getValue().matches(matchForInt));
    }
    @Test(expected=TransferException.class)
    public void exceptionCall() throws Exception {
        test.transfer();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInput() throws Exception {
        IntBoundaryValues  test2=new IntBoundaryValues(1L,100l,0,column);
        test2.construct();
        }
    @Test(expected=TransferException.class)
    public void negativeInputColumn() throws Exception {
        IntBoundaryValues test2=new IntBoundaryValues(12L,1l,0,null);
        test2.construct();
    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputEndEquallyStart() throws Exception {
        IntBoundaryValues  test3=new IntBoundaryValues(1L,1L,0,column);
        test3.construct();
    }

}