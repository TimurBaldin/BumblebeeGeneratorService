package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.reports.CsvReportRequest;
import com.rufus.bumblebee.controllers.requests.reports.ExcelReportRequest;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/reports")
public class ReportController extends BaseController {

    private ReportService service;

    @Autowired
    public ReportController(ReportService service) {
        this.service = service;
    }

    @RequestMapping(path = "/csvreport", method = RequestMethod.GET)
    public ResponseEntity<Object> csvReport(@RequestBody CsvReportRequest request) {

        byte[] file = service.createCSV(
                request.getDocName(),
                request.getDelimiter(),
                request.getContainerId()
        );
        try {
            ByteArrayResource resource = getResource(file);
            return ResponseEntity.ok().contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("text/csv")).
                            header("Content-disposition", "form-data; filename=" + request.getDocName() + ".csv")
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.ok().body(getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), new BaseResponse()));
        }

    }

    @RequestMapping(path = "/excelreport", method = RequestMethod.GET)
    public ResponseEntity<Object> excelReport(@RequestBody ExcelReportRequest request) {
        byte[] file = service.createExcel(request.getDocName(), request.getSheetName(), request.getContainerId());
        try {
            ByteArrayResource resource = getResource(file);
            return ResponseEntity.ok().contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).
                            header("Content-disposition", "attachment; filename=" + request.getDocName() + ".xlsx")
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.ok().body(getErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(), new BaseResponse()));
        }

    }

    private ByteArrayResource getResource(byte[] object) throws IOException {
        return new ByteArrayResource(object);
    }

}
