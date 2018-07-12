package com.rufus.bumblebee.Main.Factories;

import com.rufus.bumblebee.Main.Reports.TestDataCSV;
import com.rufus.bumblebee.Main.Reports.TestDataExcel;
import com.rufus.bumblebee.Main.Rules.BaseFactory;
import com.rufus.bumblebee.Main.Rules.Report.ReportCSV;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class ReportFactory implements BaseFactory {
    public ReportExcel getReportExcel() {
        return new TestDataExcel();
    }
    public ReportCSV getReportCSV() {
        return new TestDataCSV();
    }
}
