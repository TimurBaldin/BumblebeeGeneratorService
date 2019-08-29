package com.rufus.bumblebee.services;

import com.rufus.bumblebee.reports.ReportCSV;
import com.rufus.bumblebee.reports.ReportExcel;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class : Сервис создания отчетов
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
@Service
public class ReportBaseTestService {

    @Autowired
    private ReportCSV reportCSV;

    @Autowired
    private ReportExcel reportExcel;

    @Autowired
    private ContainerRepository repository;


    public byte[] createExcel(String docName, String sheetName, List<Long> containersRef) {
        byte[] file = null;
        try {
            file = reportExcel.create(docName, sheetName, loadDataByContainersRef(containersRef));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;


    }

    public byte[] createCSV(String docName, String delimiter, List<Long> containersRef) {
        byte[] file = null;
        try {
            file = reportCSV.create(docName, delimiter, loadDataByContainersRef(containersRef));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return file;

    }

    public Map<String, List<TestData>> loadDataByContainersRef(List<Long> containersRef) {
        Map<String, List<TestData>> testData = new HashMap<>();
        for (Long id : containersRef) {
            Container container = repository.getContainerById(id);
            testData.put(container.getName(), container.getData());
        }
        return testData;
    }

}
