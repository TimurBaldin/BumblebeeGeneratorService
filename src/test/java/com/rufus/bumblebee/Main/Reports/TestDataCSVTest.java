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

public class TestDataCSVTest {
    final long SIZE = 17500;
    ArrayList<String> str;
    ArrayList<Columns> arr;
    ReportService service;
    File file;

    @Before
    public void precondition() {
        service = new ReportService(new ReportFactory());
        str = new ArrayList<String>();
        arr = new ArrayList<Columns>();
        for (Integer i = 0; i <= 500; i++) {
            str.add(i.toString());
            //Количество строк
        }
        for (Integer j = 0; j <= 10; j++) {
            Column lines = new Column("Tester " + j.toString());
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
            file = service.createCSV("Test", ";", arr);
            assertTrue(file.isFile() == true);
        } catch (Exception ex) {

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
            status = file.isFile();
            assertTrue(status == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}