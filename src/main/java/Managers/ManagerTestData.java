package Managers;

import Columns.ColumnLines;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import QualifierTestGenerators.LineBoundary;
import Reports.TestDataExcel;
import Tables.StringTableBufer;
import Tools.SessionUntil;
import Tools.TestLinesRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import Rules.*;

import java.util.List;

public class ManagerTestData {
    private ApplicationContext context ;
private Columns columnLines;
private ReportExcel reportExcel=new TestDataExcel();
private Rules stringBoundaryValues;
private Rules stringSpecialValues;
TestLinesRepository tool = new TestLinesRepository();
public ManagerTestData(ApplicationContext context){
        this.context=context;
    }
@Required
    public void setColumn(String bean_name){
        columnLines =(ColumnLines)context.getBean(bean_name);
        }
    public void setStringBoundaryValues(String bean_name)throws Exception {
       stringBoundaryValues=(StringBoundaryValues)context.getBean(bean_name);
        stringBoundaryValues.construct();
        }
        public void setStringSpecialValues(String bean_name)throws Exception{
        stringSpecialValues=(StringSpecialValues)context.getBean(bean_name);
        stringSpecialValues.construct();
    }
    public void createValuesBD()throws Exception{
                 tool.create(columnLines.getValues(),columnLines.getCOLUMN());

    }
    public void createReportExcel(){
        reportExcel.create("Test","tester1",tool.get());
        SessionUntil.INSTANCE.close ();
    }

}
