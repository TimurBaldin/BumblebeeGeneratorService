package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Reports.ReportCSV;
import com.rufus.bumblebee.Main.Reports.ReportExcel;
import com.rufus.bumblebee.Main.Reports.CsvReport;
import com.rufus.bumblebee.Main.Reports.ExcelReport;
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
