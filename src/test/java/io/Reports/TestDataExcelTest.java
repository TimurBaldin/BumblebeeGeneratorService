package io.Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDataExcelTest {
   ArrayList<TypeTestData> str = new ArrayList<TypeTestData>();
   ArrayList<Columns> arr = new ArrayList<Columns>();

   @Before
   public void start() {
      for (Integer i = 0; i <= 500; i++) {
          str.add(new BaseDatatype(i.toString(),"STRING"));
          //Количество строк
       }
     arr.add(new ColumnLines("Tester1"));
       arr.add(new ColumnLines("Tester2"));
     }

   @Test
   public void create() {
       ReportService service=new ReportService();
       RepositiryTestValues service_r=new RepositiryTestValues();

       try {
          service.createExcel("Testim5", "tester2", service_r.get(arr));
       } catch (Exception e) {
            e.printStackTrace();
        }


   }
}