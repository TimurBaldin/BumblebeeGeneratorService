package Main;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import Tools.SessionUntil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Controller {

@RequestMapping("/createColumn")
    public String createColumn(@RequestParam(value="column", defaultValue="Column") String column){

        return "Column added";
    }
    @RequestMapping("/createBoundarycheck")
public String createBoundarycheck(@RequestParam(value="column", defaultValue="Boundary") String Boundarycheck){

        return "Boundarycheck added";
    }
@RequestMapping("/createSpecialcheck")
public String createSpecialcheck(@RequestParam(value="column", defaultValue="Special") String Specialcheck){

    return "Specialcheck added";
}
@RequestMapping("/getdata")
public void getdata(){


}

}

