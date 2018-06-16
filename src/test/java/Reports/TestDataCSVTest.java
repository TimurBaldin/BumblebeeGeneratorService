package Reports;

import Tables.StringTableBufer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestDataCSVTest {

@Test
public void create() {
    ArrayList<StringTableBufer> str=new  ArrayList<StringTableBufer>();
    ArrayList<StringTableBufer> str1=new  ArrayList<StringTableBufer>();
    for(Integer i=0;i<=10;i++) {
        StringTableBufer bufer = new StringTableBufer();
        bufer.setValue("Test " + i.toString());
        bufer.setColumnName("Rim");
        bufer.setUser_id(0);
        str.add(bufer);
    }
    for(Integer i=0;i<=10;i++) {
        StringTableBufer bufer = new StringTableBufer();
        bufer.setValue("Test$ " + i.toString());
        bufer.setColumnName("Rim");
        bufer.setUser_id(0);
        str1.add(bufer);
    }
    for (StringTableBufer sum:str) {
      //  System.out.println("#1"+sum.getValue());
    }
    for (StringTableBufer sum:str1) {
      //  System.out.println("#2"+sum.getValue());
    }
        TestDataCSV testDataCSV= new TestDataCSV();
        testDataCSV.create("Test_1","Rim",str);
         testDataCSV.close();
      testDataCSV.create("Test_1","Rim",str1);
         testDataCSV.close();

}
}