package com.rufus.bumblebee.Main.Repository;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RepositiryTestValuesTest {
    RepositiryTestValues testValues=new RepositiryTestValues();
    @Test
    public void create() {
        ArrayList<TypeTestData> arr=new ArrayList<TypeTestData>();
        for(Integer i=0;i<=10;i++){
            arr.add(new BaseDatatype(i.toString(),"Line"));
            }
            testValues.create(arr,"FixRealizacia");


    }

    @Test
    public void get() {
        List<Columns>arr=new ArrayList<Columns>();
        for(Integer i=0;i<=10;i++){
            arr.add(new ColumnLines("Tester1"));
        }
        List<Columns>arr1=new ArrayList<Columns>();
        arr1.addAll(testValues.get(arr));
      //  TestDataExcel testDataExcel = new TestDataExcel();
        try {
         //   testDataExcel.create("Testim78","test",arr1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}