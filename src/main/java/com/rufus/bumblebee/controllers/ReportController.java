package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/getreport")
public class ReportController {

    private ReportService service;

    @Autowired
    public ReportController(ReportService service) {
        this.service = service;
    }

    @RequestMapping(path = "/csvreport", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> csvReport(@RequestParam String docName,
                                                       @RequestParam String delimiter) {
        byte[] file = service.createCSV(docName, delimiter, null);
        try {
            ByteArrayResource resource = getResource(file);
            return ResponseEntity.ok().contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("text/csv")).
                            header("Content-disposition", "form-data; filename=" + docName + ".csv")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping(path = "/excelreport", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> excelReport(@RequestParam String docName, @RequestParam String sheetName) {
        byte[] file = service.createExcel(docName, sheetName, null);
        try {
            ByteArrayResource resource = getResource(file);
            return ResponseEntity.ok().contentLength(resource.contentLength())
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).
                            header("Content-disposition", "attachment; filename=" + docName + ".xlsx")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    private ByteArrayResource getResource(byte[] object) throws IOException {
        return new ByteArrayResource(object);
    }

}
