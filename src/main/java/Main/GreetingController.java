package Main;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import Managers.ManagerTestData;
import Tables.StringTableBufer;
import Tools.SessionUntil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GreetingController {
ManagerTestData one;
@RequestMapping("/createColumn")
    public String createColumn(@RequestParam(value="column", defaultValue="Column") String column){
        ApplicationContext context =
                new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\resources\\config.xml");
        one= new ManagerTestData(context);
        try {
            one.setColumn(column);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return "Column added";
    }
    @RequestMapping("/createBoundarycheck")
public String createBoundarycheck(@RequestParam(value="column", defaultValue="Boundary") String Boundarycheck){
        try {
            one.setStringBoundaryValues(Boundarycheck);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Boundarycheck added";
    }
@RequestMapping("/createSpecialcheck")
public String createSpecialcheck(@RequestParam(value="column", defaultValue="Special") String Specialcheck){
    try {
        one.setStringSpecialValues(Specialcheck);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Specialcheck added";
}
@RequestMapping("/getdata")
public void getdata(){
    try {
        one.createValuesBD();

    } catch (Exception e) {
        e.printStackTrace();
    }

}
@RequestMapping("/getreport")
public URI getreport(){
    one.createReportExcel();
    Path testFilePath = Paths.get("C:\\Users\\Timur\\Documents\\Spring_test\\src\\main\\java\\Report\\DOC\\Sever.xlsx");
    SessionUntil.INSTANCE.close ();
   return testFilePath.toUri();
}
}

