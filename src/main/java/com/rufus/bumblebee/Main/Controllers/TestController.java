package com.rufus.bumblebee.Main.Controllers;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Services.LinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/creatortest")
public class TestController {

    private final String MESSAGE_FORUSER_SUCCESSFULLY = "Successfully";
    private final String MESSAGE_FORUSER_ERROR = "Error";

    private LinesService service;

    @Autowired
    public TestController(LinesService service) {
        this.service = service;
    }


    @RequestMapping(path = "/setcolumn", method = RequestMethod.GET)
    public @ResponseBody
    Column setColumn(@RequestParam(value = "column") String column) {
        service.createColumn(column);
        return service.getColumn();
    }

    @RequestMapping(path = "/boundary", method = RequestMethod.GET)
    public @ResponseBody
    String createBoundarycheck(@RequestParam Integer Len, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean Low, @RequestParam Boolean Cap, @RequestParam Boolean NullValue) {
        if (service.selectionBoundaryTest(Len, INCREASE_QUANTITY, Low, Cap, NullValue)) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }
    }

    @RequestMapping(path = "/specsymbol", method = RequestMethod.GET)
    public @ResponseBody
    String createSpecialcheck(@RequestParam Integer SPECIAL_LEN, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean ESC_SPECIAL, @RequestParam Boolean SPECIAL) {
        if (service.selectionSpecialLinesTest(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL)) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }
    }

    @RequestMapping(path = "/intboundary", method = RequestMethod.GET)
    public @ResponseBody
    String createIntBoundary(@RequestParam Long BoundaryIntEnd, @RequestParam Long BoundaryIntStart, @RequestParam Integer QUANTITY) {
        if (service.selectionIntBoundary(BoundaryIntEnd, BoundaryIntStart, QUANTITY)) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }
    }

    @RequestMapping(path = "/intrange", method = RequestMethod.GET)
    public @ResponseBody
    String createIntRange(@RequestParam Long MaxIntVal, @RequestParam Long MinIntVal) {
        if (service.selectionIntRange(MaxIntVal, MinIntVal)) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }
    }


    @RequestMapping(path = "/savecolumn", method = RequestMethod.GET)
    public @ResponseBody
    String saveColumn() {
        if (service.saveColumn()) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }

    }

    @RequestMapping(path = "/savemodel", method = RequestMethod.GET)
    public @ResponseBody
    String saveModel() {
        if (service.saveModel()) {
            return MESSAGE_FORUSER_SUCCESSFULLY;
        } else {
            return MESSAGE_FORUSER_ERROR;
        }

    }


    @RequestMapping(path = "/csvreport", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> csvreport(@RequestParam String docname, @RequestParam String delimiter) {
        File file = service.createReportCSV(docname, delimiter);
        InputStreamResource resource = getResource(file);
        ;
        endwork();
        return ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/csv")).header("Content-disposition", "attachment; filename=" + file.getName())
                .body(resource);
    }

    @RequestMapping(path = "/excelreport", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> excelreport(@RequestParam String docname, @RequestParam String sheetname) {
        File file = service.createReportExcel(docname, sheetname);
        InputStreamResource resource = getResource(file);
        endwork();
        return ResponseEntity.ok().contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).header("Content-disposition", "attachment; filename=" + file.getName())
                .body(resource);

    }

    private void endwork() {
        service.endwork();
    }

    private InputStreamResource getResource(File file) {
        try {
            return new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

