package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Repository.RepositiryTestValues;
import com.rufus.bumblebee.Main.Rules.Columns;
import com.rufus.bumblebee.Main.Rules.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinesService {
    private List<Rules> Tests = new ArrayList<Rules>();
    private ColumnLines Column;
    @Autowired
    private TestsFactory lineFactory;
    private ReportService reportService;
    private List<Columns> columns = new ArrayList<Columns>();
    private RepositiryTestValues Repositiry;

    public LinesService(ReportService reportService, RepositiryTestValues Repositiry, TestsFactory lineFactory) {
        this.reportService = reportService;
        this.Repositiry = Repositiry;
        this.lineFactory = lineFactory;
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

    public boolean selectionIntBoundary(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY) {
        try {
            Tests.add(lineFactory.getIntBoundaryTest(BoundaryIntEnd, BoundaryIntStart, QUANTITY, Column));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean selectionIntRange(Long MaxIntVal, Long MinIntVal) {
        try {
            Tests.add(lineFactory.getIntFullRange(MaxIntVal, MinIntVal, Column));
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
                Tests.clear();
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
                for (Columns bufer : columns) {
                    Repositiry.create(bufer.getValues(), bufer.getCOLUMN());
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
        File file = reportService.createCSV(docname, delimiter, Repositiry.get(columns));
        cleanData();
        return file;
    }

    public File createReportExcel(String DOC_NAME, String Sheet_NAME) {
        File file = reportService.createExcel(DOC_NAME, Sheet_NAME, Repositiry.get(columns));
        cleanData();
        return file;
    }

    private void cleanData() {
        for (Columns bufer : columns) {
            bufer.clear();
        }
    }

    public ColumnLines getColumn() {
        return Column;
    }

    public boolean endwork() {
        boolean status = true;
        status = Repositiry.delete(columns);
        columns.clear();
        return status;

    }


}
