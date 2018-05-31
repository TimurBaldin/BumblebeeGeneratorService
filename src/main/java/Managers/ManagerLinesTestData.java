package Managers;

import Columns.ColumnLines;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Tools.TestLinesRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
public class ManagerLinesTestData {
    private ApplicationContext context ;
private ColumnLines columnLines;
private StringBoundaryValues stringBoundaryValues;
private StringSpecialValues stringSpecialValues;
public ManagerLinesTestData(ApplicationContext context){
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
        TestLinesRepository tool = new TestLinesRepository(columnLines.getValues(),columnLines.getCOLUMN());
        tool.create();
    }

}
