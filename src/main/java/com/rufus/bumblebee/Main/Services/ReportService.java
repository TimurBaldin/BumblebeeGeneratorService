package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Columns.Columns;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Reports.ReportCSV;
import com.rufus.bumblebee.Main.Reports.ReportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Class : Сервис создания отчетов
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class ReportService implements BaseService {

    @Autowired
    private ReportFactory config;
    private ReportCSV reportCSV;
    private ReportExcel reportExcel;

    public ReportService(ReportFactory config) {
        this.config = config;
    }

    public File createExcel(String DOC_NAME, String Sheet_NAME, List<Columns> bufer) {
        reportExcel = config.getReportExcel();
        File file = null;
        try {
            file = reportExcel.create(DOC_NAME, Sheet_NAME, bufer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;


    }

    public File createCSV(String docname, String delimiter, List<Columns> bufer) {
        reportCSV = config.getReportCSV();
        File file = null;
        try {
            file = reportCSV.create(docname, delimiter, bufer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return file;

    }

    public void deleteExcel() {
        try {
            reportExcel.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteCSV() {
        try {
            reportCSV.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
