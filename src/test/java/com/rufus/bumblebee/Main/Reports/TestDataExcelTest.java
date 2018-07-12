package com.rufus.bumblebee.Main.Reports;

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
    ArrayList<String> str;
    ArrayList<Columns> arr;
    ReportService service;
    File file;

    @Before
    public void precondition() {
        str = new ArrayList<String>();
        arr = new ArrayList<Columns>();
        service = new ReportService(new ReportFactory());
        for (Integer i = 0; i <= 500; i++) {
            str.add(i.toString());
            //Количество строк
        }
        for (Integer j = 0; j <= 10; j++) {
            ColumnLines lines = new ColumnLines("Tester " + j.toString());
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
        file = null;
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
            boolean status = file.length() >= 0;
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
            status = file.isFile();
            assertTrue(status == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}