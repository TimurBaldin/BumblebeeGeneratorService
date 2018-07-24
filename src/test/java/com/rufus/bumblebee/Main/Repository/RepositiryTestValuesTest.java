package com.rufus.bumblebee.Main.Repository;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Columns.Columns;
import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RepositiryTestValuesTest {
   private final String COLUMN_NAME="TestClassRepositiryTestValuesTest";
    private  RepositiryTestValues testValues;
    private  ArrayList<TypeTestData> arr;
    private  List<Columns> columns;
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
        columns.add(new Column(COLUMN_NAME));
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
        List<Columns> arrayList=testValues.get(columns);
        boolean status=(arrayList.size()==columns.size());
        assertTrue(status);

    }
}