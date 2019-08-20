package com.rufus.bumblebee.repository;

import com.rufus.bumblebee.container.Container;
import com.rufus.bumblebee.container.TestDataContainer;
import com.rufus.bumblebee.datatype.BaseDataType;
import com.rufus.bumblebee.datatype.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestValuesTest {
   private final String COLUMN_NAME="TestClassRepositiryTestValuesTest";
    private TestDataRepository testValues;
    private  ArrayList<TypeTestData> arr;
    private  List<Container> columns;
@Before
public void precondition(){
    testValues = new TestDataRepository();
    arr = new ArrayList<TypeTestData>();
    arr.add(new BaseDataType(null,"Line"));
    for (Integer i = 0; i <= 10; i++) {
        arr.add(new BaseDataType(i.toString(), "Line"));
    }
    columns = new ArrayList<Container>();
    for (Integer i = 0; i <= 10; i++) {
        columns.add(new TestDataContainer(COLUMN_NAME));
    }
}
@After
public void delete(){
   testValues.delete(columns);
    testValues=null;
    arr=null;
    columns=null;
}
    private boolean construct(){
       return testValues.create(arr,COLUMN_NAME);
    }
    @Test
    public void writeToBD() {
    boolean status=construct();
        assertTrue(status);
    }

    @Test
    public void delDataFromBD() {
        construct();
        boolean delstatus=testValues.delete(columns);
        assertTrue(delstatus);
        }
    @Test
    public void getDataFromBD() {
        construct();
        List<Container> arrayList=testValues.get(columns);
        boolean status=(arrayList.size()==columns.size());
        assertTrue(status);

    }
}