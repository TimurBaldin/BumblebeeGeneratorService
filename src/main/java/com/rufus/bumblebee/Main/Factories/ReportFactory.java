package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Reports.ReportCSV;
import com.rufus.bumblebee.Main.Reports.ReportExcel;
import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;

public class ReportFactory {

    public ReportExcel getReportExcel() {
        return new TestDataExcel();
    }

    public ReportCSV getReportCSV() {
        return new TestDataCSV();
    }

}
