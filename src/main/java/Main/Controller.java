package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Columns.ColumnLines;
import Services.LinesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/linetest")
public class Controller {
LinesService service = new LinesService();

@RequestMapping(path = "/home", method = RequestMethod.POST)
public @ResponseBody ColumnLines setColumn(@RequestBody String column) {
    service.createColumn(column);
    return service.getColumn();
}

@RequestMapping(path = "/boundary", method = RequestMethod.PUT)
public void createBoundarycheck(@RequestParam Integer Len,@RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean Low,@RequestParam Boolean Cap,@RequestParam Boolean NullValue){
service.selectionBoundaryTest(Len,INCREASE_QUANTITY,Low,Cap,NullValue);
}

@RequestMapping(path = "/specsymbol", method = RequestMethod.PUT)
public void createSpecialcheck(@RequestParam Integer SPECIAL_LEN, @RequestParam Integer INCREASE_QUANTITY, @RequestParam Boolean ESC_SPECIAL,@RequestParam Boolean SPECIAL){
    service.selectionSpecialLinesTest(SPECIAL_LEN,INCREASE_QUANTITY,ESC_SPECIAL,SPECIAL);
}

@RequestMapping(path = "/csvreport", method = RequestMethod.GET)
public ResponseEntity<InputStreamResource> csvreport(@RequestBody String DOC_NAME, String delimetr) {
service.createReportCSV(DOC_NAME,delimetr);
File file=service.createReportCSV(DOC_NAME,delimetr);
    InputStreamResource resource = null;
    try {
        resource = new InputStreamResource(new FileInputStream(file));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return ResponseEntity.ok().contentLength(file.length())
                   .contentType(MediaType.parseMediaType("application/octet-stream"))
                   .body(resource);
}

}

