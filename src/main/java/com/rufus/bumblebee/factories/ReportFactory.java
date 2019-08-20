package com.rufus.bumblebee.factories;

import com.rufus.bumblebee.reports.ReportCSV;
import com.rufus.bumblebee.reports.ReportExcel;
import com.rufus.bumblebee.reports.CsvReport;
import com.rufus.bumblebee.reports.ExcelReport;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ReportFactory {

    public ReportExcel getReportExcel() {
        return new ExcelReport();
    }

    public ReportCSV getReportCSV() {
        return new CsvReport();
    }

}
