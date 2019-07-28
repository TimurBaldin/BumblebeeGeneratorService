package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringBoundaryValues;
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
    private TestDataContainer testDataContainer;

    @Before
    public void precondition() {
        testDataContainer = new TestDataContainer("Test");
        test = new StringBoundaryValues(12, 1, true, false, true, testDataContainer);
        test1 = new StringBoundaryValues(12, 1, true, true, true, testDataContainer);
        test2 = new StringBoundaryValues(12, 1, false, true, false, testDataContainer);
    }
    @After
    public void delete() {
        testDataContainer = null;
        test = null;
        test1 = null;
        test2 = null;
    }
    private void construct(StringBoundaryValues bufer){
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
    public void testSizeWords() {
        try {
            construct(test);
            assertTrue(testDataContainer.getValues().size() == Len);
            testDataContainer.clear();
            construct(test1);
            assertTrue(testDataContainer.getValues().size() == Len);
            testDataContainer.clear();
            construct(test2);
            assertTrue(testDataContainer.getValues().size() == notNullLen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandomValueLow() {
        try {
            construct(test);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(getValue().matches(matchForLow));


    }

    @Test
    public void testRandomValueCap() {
        try {
            construct(test2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(getValue().matches(matchForCap));
    }

    @Test
    public void testRandomValueCapLow() {
        try {
            construct(test1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean val1 = getValue().matches(matchForLow);
        boolean val2 = getValue().matches(matchForCap);
        Assert.assertEquals(val1, false);
        Assert.assertEquals(val2, false);

    }
    @Test(expected=TransferException.class)
    public void exceptionCall() throws Exception {
        test.transfer();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputLowCap() throws Exception {
        StringBoundaryValues  test = new StringBoundaryValues(12, 1, false, false, false, testDataContainer);
        test.construct();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputLen() throws Exception {
        StringBoundaryValues  test = new StringBoundaryValues(0, 1, true, true, false, testDataContainer);
        test.construct();
        }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputQUANTITY() throws Exception {
        StringBoundaryValues  test = new StringBoundaryValues(12, -1, true, true, false, testDataContainer);
        test.construct();
        }

}