package com.rufus.bumblebee.services;

import com.rufus.bumblebee.container.Container;
import com.rufus.bumblebee.factories.ReportFactory;
import com.rufus.bumblebee.reports.ReportCSV;
import com.rufus.bumblebee.reports.ReportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class : Сервис создания отчетов
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class ReportTestService implements BaseTestService {

    @Autowired
    private ReportFactory config;
    private ReportCSV reportCSV;
    private ReportExcel reportExcel;

    public ReportTestService(ReportFactory config) {
        this.config = config;
    }

    public byte[] createExcel(String docName, String sheetName, List<Container> bufer) {
        reportExcel = config.getReportExcel();
        byte[] file = null;
        try {
            file = reportExcel.create(docName, sheetName, bufer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;


    }

    public byte[] createCSV(String docName, String delimiter, List<Container> bufer) {
        reportCSV = config.getReportCSV();
        byte[] file = null;
        try {
            file = reportCSV.create(docName, delimiter, bufer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return file;

    }



    @Override
    public void startGeneratingData() {
        return false;
    }

    @Override
    public void saveTests() {
        return false;
    }
}
