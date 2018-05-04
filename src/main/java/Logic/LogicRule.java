package Logic;
<<<<<<< HEAD
import TipDataRule.*;

public class LogicRule {
    public static void main(String[] args) {
        LogicRule l=new LogicRule ();
        l.interaction ();
        }
    public void interaction (){
=======

import TipDataRule.RuleString;

public class LogicRule {
    public static void main(String[] args) {
        LogicRule l = new LogicRule();
        l.interaction();
        /*UserTest test= new UserTest ();
        test.test ();*/


    }

    public void interaction() {
>>>>>>> 8c2057f5290e03dd3abc1132acb8c965c3ed7b26
        /*
        Пример входных пользовательских данных
         */
        RuleString RS = new RuleString(80, 30, false, true, true, true, "Test");
        RuleString RT = new RuleString(80, 30, false, true, true, true, "New val");
        try {
            RS.buildTestData();
            RT.buildTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
