package com.rufus.bumblebee.Main.Services;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;
import com.rufus.bumblebee.Main.Factories.TestsFactory;
import com.rufus.bumblebee.Main.Generators.Rule;
import com.rufus.bumblebee.Main.Repository.TestDataRepository;
import com.rufus.bumblebee.Main.Columns.Columns;

import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Class : Сервис создания тестовых данных
 * @version : 0.0.1
 * @author : Baldin Timur
 */
@Service
public class TestDataService implements BaseService {

    private List<Rule> Tests = new ArrayList<Rule>();
    private Column Column;
    @Autowired
    private TestsFactory lineFactory;
    private ReportService reportService;
    private List<Columns> columns = new ArrayList<Columns>();
    private TestDataRepository Repositiry;

    public TestDataService(ReportService reportService, TestDataRepository repositiry, TestsFactory lineFactory) {
        this.reportService = reportService;
        this.Repositiry = repositiry;
        this.lineFactory = lineFactory;
    }

    public void createColumn(String column_name) {
        Column = lineFactory.getColumn(column_name);
    }

    public boolean selectionBoundaryTest(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue) {
        try {
            Rule bufer=lineFactory.getBoundaryValues(Len, INCREASE_QUANTITY, Low, Cap, NullValue, Column);
            Tests.add(bufer);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean selectionSpecialLinesTest(Integer SPECIAL_LEN, Integer INCREASE_QUANTITY, Boolean ESC_SPECIAL, Boolean SPECIAL) {
        try {
            Rule bufer=lineFactory.getSpecialValues(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL, Column);
            Tests.add(bufer);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean selectionIntBoundary(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY) {
        try {
            Rule bufer=lineFactory.getIntBoundaryTest(BoundaryIntEnd, BoundaryIntStart, QUANTITY, Column);
            Tests.add(bufer);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean selectionIntRange(Long MaxIntVal, Long MinIntVal) {
        try {
            Rule bufer=lineFactory.getIntFullRange(MaxIntVal, MinIntVal, Column);
            Tests.add(bufer);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveColumn() {
        boolean status=false;
        try {
            if (Tests.size() > 0) {
                for (Rule bufer : Tests) {
                    bufer.construct();
                }
                columns.add(Column);
                Tests.clear();
            } else {
                throw new InvalidInputException("It is necessary to choose checks");
            }
        } catch (GeneratorExceptionInputOptions ex){
             System.out.println(ex.getMessage());
             System.out.println(ex.getParameters());
             return status;
        } catch (TransferException e) {
            System.out.println(e.getMessage());
            return status;
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return status;
        }
        status=true;
        return status;

    }

    public boolean saveModel() {
        boolean status=false;
        try {
            if (columns.size() > 0) {
                for (Columns bufer : columns) {
                    Repositiry.create(bufer.getValues(), bufer.getColumn());
                    bufer.clear();
                }
            } else {
                throw new InvalidInputException("It is necessary to choose checks");
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return status;
        }
        status=true;
        return status;
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
            bufer.cleanReportData();
        }
    }

    public Column getColumn() {
        return Column;
    }

    public boolean endwork() {
        boolean status = true;
        status = Repositiry.delete(columns);
        columns.clear();
        return status;

    }


}
