package com.rufus.bumblebee.NumberGenerator;

import com.rufus.bumblebee.exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.generators.NumberGenerator.IntFullRange;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/*
public class IntFullRangeTest {
    private final int Len = 10;
    private final String matchForInt = "[0-9]*";
    private IntFullRange test;
    private TestDataContainer testDataContainer;
    @Before
    public void precondition() {
        testDataContainer = new TestDataContainer("Test");
        test=new IntFullRange(10L,1L, testDataContainer);
    }
    @After
    public void delete(){
        testDataContainer =null;
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
        test.receivingTestData();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInput() throws Exception {
        IntFullRange  test2=new IntFullRange(1L,100l, testDataContainer);
        test2.construct();
        }
    @Test(expected=TransferException.class)
    public void negativeInputColumn() throws Exception {
        IntFullRange test2=new IntFullRange(12L,1l,null);
        test2.construct();
    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputEndEquallyStart() throws Exception {
        IntFullRange  test3=new IntFullRange(1L,1L, testDataContainer);
        test3.construct();
    }

}

 */