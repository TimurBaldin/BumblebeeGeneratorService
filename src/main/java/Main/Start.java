package Main;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Columns.*;
import Managers.ManagerLinesTestData;
import NumberGenerator.IntBoundaryValues;
import Tools.SessionUntil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class Start {
    public static void main(String[] args) {
      ApplicationContext context =
                new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\main\\resources\\config.xml");
        try {
      ManagerLinesTestData one= new ManagerLinesTestData(context);

      one.setColumn("Column");
      one.setStringBoundaryValues("Boundary");
      one.setStringSpecialValues("Special");
      one.createValuesBD();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SessionUntil.INSTANCE.close ();
        }



    }




}
