package com.rufus.bumblebee.NumberGenerator;

import com.rufus.bumblebee.container.TestDataContainer;
import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.generators.NumberGenerator.IntBoundaryValues;
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
    private TestDataContainer testDataContainer;
    @Before
    public void precondition() {
        testDataContainer = new TestDataContainer("Test");
        test=new IntBoundaryValues(10L,1L,2, testDataContainer);
        test1=new IntBoundaryValues(10L,1L,0, testDataContainer);
    }
    @After
    public void delete(){
        testDataContainer =null;
        test=null;
        test1=null;
    }
    private void construct(IntBoundaryValues bufer){
        try {
            bufer.construct();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getValue(){
        List<TypeTestData> arrayList = testDataContainer.getValues();
        String buffer = (String) arrayList.get(arrayList.size() / 2).getValue();
        return buffer;
    }
    @Test
    public void sizeValuesTest(){
        try {
            construct(test);
            assertTrue(testDataContainer.getValues().size() == Len);
            testDataContainer.clear();

            construct(test1);
            assertTrue(testDataContainer.getValues().size() == noQuantity);
            testDataContainer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testRandomValue() {
        try {
            construct(test1);
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
        IntBoundaryValues  test2=new IntBoundaryValues(1L,100l,0, testDataContainer);
        test2.construct();
        }
    @Test(expected=TransferException.class)
    public void negativeInputColumn() throws Exception {
        IntBoundaryValues test2=new IntBoundaryValues(12L,1l,0,null);
        test2.construct();
    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputEndEquallyStart() throws Exception {
        IntBoundaryValues  test3=new IntBoundaryValues(1L,1L,0, testDataContainer);
        test3.construct();
    }
}