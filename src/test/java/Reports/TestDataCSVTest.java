package Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestDataCSVTest {
ArrayList<String> str=new ArrayList<String>();
ArrayList<String> str1=new ArrayList<String>();
ArrayList<ColumnLines> arr=new ArrayList<ColumnLines>();
TestDataCSV testDataCSV;
@Before
public void start(){
    testDataCSV= new TestDataCSV();
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
    try {
            testDataCSV.create("Test_1",";",arr);
            }catch (Exception ex){

        }
    try {
        testDataCSV.delete();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

}

@Test
public void delete(){
    try {
        testDataCSV.delete();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}


}