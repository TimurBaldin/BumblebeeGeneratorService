package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Services.ReportService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestDataExcelTest {
   private ArrayList<String> str;
   private ArrayList<Columns> arr;
   private ReportService service;
    private final int QUANTITY_LINES=500;
    private final String COLUMN_NAME="Tester";
    private final int QUANTITY_COLUMN=10;
    private final String DOC_NAME="Test";
    private final String Sheet_Name="test1";

    @Before
    public void precondition() {
        str = new ArrayList<String>();
        arr = new ArrayList<Columns>();
        service = new ReportService(new ReportFactory());
        for (Integer i = 0; i <= QUANTITY_LINES; i++) {
            str.add(i.toString());
            //Количество строк
        }
        for (Integer j = 0; j <= QUANTITY_COLUMN; j++) {
            Column lines = new Column(COLUMN_NAME + j.toString());
            lines.setReport(str);
            arr.add(lines);
            //Количество колонок
        }
    }

    @After
    public void postcondition() {
        str.clear();
        arr.clear();
        service = null;

    }
    private File construct(String docname,String Sheet_Name,ArrayList<Columns> arr){
        return service.createExcel(docname, Sheet_Name, arr);
    }

    @Test
    public void create() {
        try {
            assertTrue( construct(DOC_NAME,Sheet_Name,arr).isFile() == true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkSize() {
        try {
            boolean status =  construct(DOC_NAME,Sheet_Name,arr).length() >= 0;
            assertTrue(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
          File  file = construct(DOC_NAME,Sheet_Name,arr);
            service.deleteExcel();
            boolean status = true;
            status = file.isFile();
            assertTrue(status == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}