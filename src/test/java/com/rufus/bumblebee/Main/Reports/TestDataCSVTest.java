package com.rufus.bumblebee.Main.Reports;

import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Container.Container;
import com.rufus.bumblebee.Main.Services.ReportTestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestDataCSVTest {
   private final long SIZE = 17500;
    private ArrayList<String> str;
    private  ArrayList<Container> arr;
    private ReportTestService service;
    private final int QUANTITY_LINES=500;
    private final String COLUMN_NAME="Tester";
    private final int QUANTITY_COLUMN=10;
    private final String DOC_NAME="Test";
    private final String DELIMITER=";";

    @Before
    public void precondition() {
        service = new ReportTestService(new ReportFactory());
        str = new ArrayList<String>();
        arr = new ArrayList<Container>();
        for (Integer i = 0; i <= QUANTITY_LINES; i++) {
            str.add(i.toString());
            //Количество строк
        }
        for (Integer j = 0; j <= QUANTITY_COLUMN; j++) {
            TestDataContainer lines = new TestDataContainer(COLUMN_NAME + j.toString());
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
    private File construct(String docname,String delimiter,ArrayList<Container> arr){
        return service.createCSV(docname, delimiter, arr);
        }

    @Test
    public void create() {
        try {
            assertTrue( construct(DOC_NAME,DELIMITER,arr).isFile() == true);
        } catch (Exception ex) {

        }

    }

    @Test
    public void checkSize() {
        try {
            boolean status = (construct(DOC_NAME,DELIMITER,arr).length() >= SIZE);
            assertTrue(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            File file = construct(DOC_NAME,DELIMITER,arr);
            service.deleteCSV();
            boolean status = true;
            status = file.isFile();
            assertTrue(status == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}