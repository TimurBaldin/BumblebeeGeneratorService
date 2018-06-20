package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Factories.ReportFactory;
import com.rufus.bumblebee.Main.Rules.Report.ReportCSV;
import com.rufus.bumblebee.Main.Rules.Report.ReportExcel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReportService {
   private ReportFactory configiration=new ReportFactory();
   private ReportCSV reportCSV=configiration.getReportCSV();
private ReportExcel reportExcel=configiration.getReportExcel();
    public File createExcel(String DOC_NAME, String Sheet_NAME, List<ColumnLines> bufer){
        File file=null;
        try {
            file=reportExcel.create(DOC_NAME,Sheet_NAME,bufer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;


    }
    public File createCSV(String docname,String delimiter,List<ColumnLines> bufer){
        try {
        return reportCSV.create(docname, delimiter,bufer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

}
