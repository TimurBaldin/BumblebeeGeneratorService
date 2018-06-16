package Reports;

import Columns.ColumnLines;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestDataExcelTest {
ArrayList<String> str=new ArrayList<String>();
ArrayList<String> str1=new ArrayList<String>();
ArrayList<ColumnLines> arr=new ArrayList<ColumnLines>();
@Before
public void start(){
    for(Integer i=0;i<=5000;i++){
        str.add(i.toString());
        //Количество строк
    }
    for(Integer i=0;i<=58;i++){
        ColumnLines bufer=new ColumnLines("Tester");
        bufer.setValues(str);
        arr.add(bufer);
        //Количество колонок

    }


}
@Test
public void create() {
    TestDataExcel testDataExcel=new TestDataExcel();


    try {
        testDataExcel.create("Testim","tester1",arr);
    } catch (Exception e) {
        e.printStackTrace();
    }


}
}