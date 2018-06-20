package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Factories.LineFactory;
import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Rules.Rules;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinesService {
private List<Rules> Tests = new ArrayList<Rules>();
private ColumnLines Column;
private LineFactory lineFactory = new LineFactory();
private ReportService reportService;
private List<ColumnLines> columns = new ArrayList<ColumnLines>();
private RepositiryTestValues Repositiry;

public LinesService(ReportService reportService, RepositiryTestValues Repositiry) {
    this.reportService = reportService;
    this.Repositiry = Repositiry;
}

public void createColumn(String column_name) {
    Column = lineFactory.getColumn(column_name);
}

public boolean selectionBoundaryTest(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue) {
    try {
        Tests.add(lineFactory.getBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, Column));
    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
    return true;

}

public boolean selectionSpecialLinesTest(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL) {
    try {
        Tests.add(lineFactory.getSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, Column));
    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
    return true;
}

public boolean saveColumn() {
    try {
        if (Tests.size() > 0) {
            for (Rules bufer : Tests) {
                bufer.construct();
            }
            columns.add(Column);
        } else {
            throw new Exception("It is necessary to choose checks");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;

}

public boolean saveModel() {
    try {
        if (columns.size() > 0) {
            for (ColumnLines bufer : columns) {
                Repositiry.setLines(bufer.getValues(), bufer.getCOLUMN());
                bufer.clear();
            }
        } else {
            throw new Exception("It is necessary to choose checks");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;
}

public File createReportCSV(String docname, String delimiter) {
    return reportService.createCSV(docname, delimiter, Repositiry.getLines(columns));
}

public File createReportExcel(String DOC_NAME, String Sheet_NAME) {
    return reportService.createExcel(DOC_NAME, Sheet_NAME, Repositiry.getLines(columns));
}

public ColumnLines getColumn() {
    return Column;
}


}
