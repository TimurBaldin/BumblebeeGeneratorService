package com.rufus.bumblebee.Main.LineGenerator;

import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import com.rufus.bumblebee.Main.Generators.LineGenerator.StringSpecialValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static com.rufus.bumblebee.Main.Generators.LineGenerator.SpecialID.KEY_ID;
import static org.junit.Assert.*;

public class StringSpecialValuesTest {
    private final int Len = 13;
    private final String matchSpecial = "[^A-ZА-Яa-z]*";
    private StringSpecialValues test;
    private StringSpecialValues test1;
    private StringSpecialValues test2;
    private TestDataContainer testDataContainer;
    private final int MIN_ID_ESC = KEY_ID.getMIN_ID_ESC();
    private final int MAX_ID_ESC = KEY_ID.getMAX_ID_ESC();
    //Escape symbols
    private final int MIN_ID_SPECIAL_1 = KEY_ID.getMIN_ID_SPECIAL_1();
    private final int MAX_ID_SPECIAL_1 = KEY_ID.getMAX_ID_SPECIAL_1();
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2 = KEY_ID.getMIN_ID_SPECIAL_2();
    private final int MAX_ID_SPECIAL_2 = KEY_ID.getMAX_ID_SPECIAL_2();

    @Before
    public void precondition() {
        testDataContainer = new TestDataContainer("Test");
        test = new StringSpecialValues(12, 1, true, false, testDataContainer);
        test1 = new StringSpecialValues(12, 1, true, true, testDataContainer);
        test2 = new StringSpecialValues(13, 13, false, true, testDataContainer);

    }

    @After
    public void delete() {
        testDataContainer = null;
        test = null;
        test1 = null;
        test2 = null;
    }
    private void construct(StringSpecialValues bufer){
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
            assertTrue(testDataContainer.getValues().size() == Len * 2);
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
                construct(bufer);
                assertTrue(getValue().matches(matchSpecial));
                testDataContainer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValueIDSpecial() {
        try {
            construct(test2);
            char [] testchars=getValue().toCharArray();
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
            construct(test);
            char [] testchars=getValue().toCharArray();
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
            construct(test1);
            char [] testchars=getValue().toCharArray();
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
    @Test(expected=TransferException.class)
    public void exceptionCall() throws Exception {
        test.transfer();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputESCandSPECIAL() throws Exception {
        StringSpecialValues  test = new StringSpecialValues(12, 1, false, false, testDataContainer);
        test.construct();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputLen() throws Exception {
        StringSpecialValues  test = new StringSpecialValues(0, 1, true, false, testDataContainer);
        test.construct();

    }
    @Test(expected=GeneratorExceptionInputOptions.class)
    public void negativeInputLenQUANTITY() throws Exception {
        StringSpecialValues  test = new StringSpecialValues(12, -1, true, false, testDataContainer);
        test.construct();

    }


}