package Logic;
import TipDataRule.*;

public class LogicRule {
    public static void main(String[] args) {
        LogicRule l=new LogicRule ();
        l.interaction ();



    }
    public void interaction (){
        /*
        Пример входных пользовательских данных
         */
        RuleString RS=new RuleString(1000,30,false,true,true,true,"Test");
        try {
            RS.buildTestData ();
        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }
}
