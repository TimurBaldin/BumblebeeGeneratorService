package com.rufus.bumblebee.services;

import com.rufus.bumblebee.reports.ReportCSV;
import com.rufus.bumblebee.reports.ReportExcel;
import com.rufus.bumblebee.repository.ContainerRepository;
import com.rufus.bumblebee.tables.Container;
import com.rufus.bumblebee.tables.TestData;
import com.sun.media.sound.InvalidDataException;
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
public class ReportService {


    private ReportCSV reportCSV;


    private ReportExcel reportExcel;


    private ContainerRepository repository;

    @Autowired
    public ReportService(ReportCSV reportCSV, ReportExcel reportExcel, ContainerRepository repository) {
        this.reportCSV = reportCSV;
        this.reportExcel = reportExcel;
        this.repository = repository;
    }

    public byte[] createExcel(String docName, String sheetName, List<Long> containersRef) {
        byte[] file = null;
        try {
            file = reportExcel.create(
                    docName,
                    sheetName,
                    loadDataByContainersRef(containersRef));
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

    private Map<Container, List<TestData>> loadDataByContainersRef(List<Long> containersRef) throws InvalidDataException {
        Map<Container, List<TestData>> testData = new HashMap<>();
        for (Long id : containersRef) {
            Container container = repository.getContainerById(id);
            testData.put(container, container.getData());
        }
        return testData;
    }

}
