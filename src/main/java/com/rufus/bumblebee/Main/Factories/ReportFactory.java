package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Rules.Report.ReportCSV;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;

public class ReportFactory {
public ReportCSV getReportCSV() {
    return new TestDataCSV();
}

public ReportExcel getReportExcel() {
    return new TestDataExcel();
}
}
