package io.Reports;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.TypeTestData;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestDataExcelTest {
    ArrayList<String> str = new ArrayList<String>();
    ArrayList<Columns> arr = new ArrayList<Columns>();
    ReportService service;
    final long SIZE = 1000000;
    File file;

    @Before
    public void start() {
        service = new ReportService(new ReportFactory());
        for (Integer i = 0; i <= 5000; i++) {
            str.add(i.toString());
            //Количество строк
        }
        for (Integer j = 0; j <= 1000; j++) {
            ColumnLines lines = new ColumnLines("Tester " + j.toString());
            lines.setReport(str);
            arr.add(lines);
            //Количество колонок
        }
    }
    @After
    public void del(){
        str.clear();
        arr.clear();
        service=null;
        file=null;
    }

    @Test
    public void create() {
        try {
            file = service.createExcel("Test", "test", arr);
            assertTrue(file.isFile() == true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkSize() {
        try {
            file = service.createExcel("Test", "test", arr);
            boolean status = file.length() >= SIZE;
            assertTrue(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            file = service.createExcel("Test", "test", arr);
            service.deleteExcel();
            boolean status = true;
            status=file.isFile();
            assertTrue(status == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}