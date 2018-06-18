package Main;
import Columns.ColumnLines;
import Reports.*;
import Services.LinesService;
import Tools.SessionUntil;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication(scanBasePackages="Main")
public class Start {
    public static void main(String[] args) {
//      ApplicationContext context =
           //     new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\resources\\config.xml");
     SpringApplication.run(Start.class, args);




    }
}
