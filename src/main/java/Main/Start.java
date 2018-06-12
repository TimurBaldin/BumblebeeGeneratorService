package Main;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Columns.*;
import Managers.ManagerTestData;
import Managers.ManagerTestData;
import NumberGenerator.IntBoundaryValues;
import Tools.SessionUntil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\resources\\config.xml");
       // SpringApplication.run(Start.class, args);
        ManagerTestData managerTestData=new ManagerTestData(context);
        managerTestData.setColumn("Column");
        try {
            managerTestData.setStringBoundaryValues("Boundary");
            managerTestData.createValuesBD();
            managerTestData.createReportExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
