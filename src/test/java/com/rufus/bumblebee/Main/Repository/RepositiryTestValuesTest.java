package com.rufus.bumblebee.Main.Repository;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RepositiryTestValuesTest {
    final String COLUMN_NAME="TestClassRepositiryTestValuesTest";
    RepositiryTestValues testValues;
    ArrayList<TypeTestData> arr;
    List<Columns> columns;
@Before
public void precondition(){
    testValues = new RepositiryTestValues();
    arr = new ArrayList<TypeTestData>();
    arr.add(new BaseDatatype(null,"Line"));
    for (Integer i = 0; i <= 10; i++) {
        arr.add(new BaseDatatype(i.toString(), "Line"));
    }
    columns = new ArrayList<Columns>();
    for (Integer i = 0; i <= 10; i++) {
        columns.add(new Column("TestClassRepositiryTestValuesTest"));
    }
}
@After
public void delete(){
   testValues.delete(columns);
    testValues=null;
    arr=null;
    columns=null;
}
    @Test
    public void writeToBD() {
    boolean status=testValues.create(arr, "TestClassRepositiryTestValuesTest");
        assertTrue(status);
    }

    @Test
    public void delDataFromBD() {
        testValues.create(arr, "TestClassRepositiryTestValuesTest");
        boolean delstatus=testValues.delete(columns);
        assertTrue(delstatus);
        }
    @Test
    public void getDataFromBD() {
        testValues.create(arr, "TestClassRepositiryTestValuesTest");
        List<Columns> arrayList=testValues.get(columns);
        boolean status=(arrayList.size()==columns.size());
        assertTrue(status);

    }
}