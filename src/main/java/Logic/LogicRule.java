package Logic;
import Tables.UserTest;
import TipDataRule.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
public class LogicRule {
    public static void main(String[] args) {
        LogicRule l=new LogicRule ();
        l.interaction ();



    }
    public void interaction (){
        /*
        Пример входных пользовательских данных
         */
        RuleString RS=new RuleString(10,30,true,true,true,true);
        try {
            RS.buildTestData ();
        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }
}
