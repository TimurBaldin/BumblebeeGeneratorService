package Services;

import Columns.ColumnLines;
import Factories.ReportConfigiration;
import Rules.Report.ReportCSV;
import Rules.Report.ReportExcel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReportService {
   private ReportConfigiration configiration;
{
     configiration=new ReportConfigiration();
}
private ReportCSV reportCSV=configiration.getReportCSV();
private ReportExcel reportExcel=configiration.getReportExcel();
    public boolean createExcel(String DOC_NAME, String Sheet_NAME, List<ColumnLines> bufer){
        try {
            reportExcel.create(DOC_NAME,Sheet_NAME,bufer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public File createCSV(String DOC_NAME,String delimetr,List<ColumnLines> bufer){
        try {
        return reportCSV.create(DOC_NAME,delimetr,bufer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }


}
