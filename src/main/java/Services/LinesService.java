package Services;
import Repository.RepositiryTestValues;
import Rules.*;
import Factories.LineFactory;
import Columns.ColumnLines;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LinesService {
    private List<Rules> Tests;
    private ColumnLines Column;
    private LineFactory lineFactory;
    private ReportService reportService=lineFactory.getReportService();
    {
    lineFactory=new LineFactory();
    }
    private List<ColumnLines> columns;
    private RepositiryTestValues Repositiry=lineFactory.getRepositiry();
    public void createColumn(String column_name){
        Column=lineFactory.getColumn(column_name);
    }
    public void selectionBoundaryTest(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap,Boolean NullValue){
        Tests.add(lineFactory.getBoundaryValues(Len,INCREASE_QUANTITY,Low,Cap,NullValue,Column));
}
    public void selectionSpecialLinesTest(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL){
        Tests.add(lineFactory.getSpecialValues(SPECIAL_LEN,INCREASE_QUANTITY,ESC_SPECIAL,SPECIAL,Column));
    }
    public void saveColumn(){
        try {
            if(Tests.size()>0){
                for (Rules bufer:Tests) {
                    bufer.construct();
                    }
                    columns.add(Column);
            }else {
                throw new Exception("It is necessary to choose checks");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void saveModel(){
        try {
            if(columns.size()>0){
                for (ColumnLines bufer:columns) {
                    Repositiry.setLines(bufer.getValues(),bufer.getCOLUMN());
                    bufer.clear();
                }
                }else {
                throw new Exception("It is necessary to choose checks");
            }
            }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createReport(){
        Repositiry.getLines(columns);

    }



}
