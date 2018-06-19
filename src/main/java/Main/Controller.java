package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Columns.ColumnLines;
import Services.LinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/linetest")
public class Controller {
private final String MESSAGE_FORUSER_SUCCESSFULLY = "Операция успешно выполнена";
private final String MESSAGE_FORUSER_ERROR = "Ошибка,обратитесь к администратору";
private LinesService service;

@Autowired
public Controller(LinesService service) {
    this.service = service;
}


@RequestMapping(path = "/home", method = RequestMethod.POST)
public @ResponseBody
ColumnLines setColumn(@RequestBody String column) {
    service.createColumn(column);
    return service.getColumn();
}

@RequestMapping(path = "/boundary", method = RequestMethod.PUT)
public String createBoundarycheck(@RequestParam Integer Len, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean Low, @RequestParam Boolean Cap, @RequestParam Boolean NullValue) {
    if (service.selectionBoundaryTest(Len, INCREASE_QUANTITY, Low, Cap, NullValue)) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }
}

@RequestMapping(path = "/specsymbol", method = RequestMethod.PUT)
public String createSpecialcheck(@RequestParam Integer SPECIAL_LEN, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean ESC_SPECIAL, @RequestParam Boolean SPECIAL) {
    if (service.selectionSpecialLinesTest(SPECIAL_LEN, INCREASE_QUANTITY, ESC_SPECIAL, SPECIAL)) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }
}

@RequestMapping(path = "/savecolumn", method = RequestMethod.PUT)
public String saveColumn() {
    if (service.saveColumn()) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }

}

@RequestMapping(path = "/savemodel", method = RequestMethod.PUT)
public String saveModel() {
    if (service.saveModel()) {
        return MESSAGE_FORUSER_SUCCESSFULLY;
    } else {
        return MESSAGE_FORUSER_ERROR;
    }

}


@RequestMapping(path = "/csvreport", method = RequestMethod.GET)
public ResponseEntity<InputStreamResource> csvreport(@RequestBody String DOC_NAME, String delimetr) {
    service.createReportCSV(DOC_NAME, delimetr);
    File file = service.createReportCSV(DOC_NAME, delimetr);
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

}

