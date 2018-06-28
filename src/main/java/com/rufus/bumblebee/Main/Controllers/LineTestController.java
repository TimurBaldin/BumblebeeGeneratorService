package com.rufus.bumblebee.Main.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.springframework.stereotype.Controller;
import com.rufus.bumblebee.Main.Columns.ColumnLines;
import com.rufus.bumblebee.Main.Services.LinesService;
import com.rufus.bumblebee.Main.Tools.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/creatortest")
public class LineTestController {
private final String MESSAGE_FORUSER_SUCCESSFULLY = "Операция успешно выполнена";
private final String MESSAGE_FORUSER_ERROR = "Ошибка,обратитесь к администратору";

private LinesService service;
@Autowired
public LineTestController(LinesService service) {
    this.service = service;
}


@RequestMapping(path = "/home", method = RequestMethod.GET)
public @ResponseBody
ColumnLines setColumn(@RequestParam(value="column") String column) {
    service.createColumn(column);
    return service.getColumn();
}

@RequestMapping(path = "/boundary", method = RequestMethod.GET)
public @ResponseBody String createBoundarycheck(@RequestParam Integer Len, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean Low, @RequestParam Boolean Cap, @RequestParam Boolean NullValue) {
    if (service.selectionBoundaryTest(Len, INCREASE_QUANTITY, Low, Cap, NullValue)) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }
}

@RequestMapping(path = "/specsymbol", method = RequestMethod.GET)
public @ResponseBody String createSpecialcheck(@RequestParam Integer SPECIAL_LEN, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean ESC_SPECIAL, @RequestParam Boolean SPECIAL) {
    if (service.selectionSpecialLinesTest(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL)) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }
}

@RequestMapping(path = "/savecolumn", method = RequestMethod.GET)
public @ResponseBody String saveColumn() {
    if (service.saveColumn()) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }

}

@RequestMapping(path = "/savemodel", method = RequestMethod.GET)
public @ResponseBody String saveModel() {
    if (service.saveModel()) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }

}


@RequestMapping(path = "/csvreport", method = RequestMethod.GET)
public ResponseEntity<InputStreamResource> csvreport(@RequestParam String docname, @RequestParam String delimiter) {
    File file = service.createReportCSV(docname, delimiter);
    InputStreamResource resource = null;
    try {
        resource = new InputStreamResource(new FileInputStream(file));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return ResponseEntity.ok().contentLength(file.length())
                   .contentType(MediaType.parseMediaType("text/csv"))
                   .body(resource);
}

@RequestMapping(path = "/excelreport",method = RequestMethod.GET)
public ResponseEntity<InputStreamResource> excelreport(@RequestParam String docname,@RequestParam String sheetname){
  File file =service.createReportExcel(docname, sheetname);
    InputStreamResource resource = null;
    try {
        resource = new InputStreamResource(new FileInputStream(file));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return ResponseEntity.ok().contentLength(file.length())
                   .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                   .body(resource);
}
@RequestMapping(path = "",method = RequestMethod.GET)
public @ResponseBody String test(){
    return "OK";
}
}

