package Logic;

import TipDataRule.RuleString;

public class LogicRule {
    public static void main(String[] args) {
        LogicRule l = new LogicRule();
        l.interaction();
        /*UserTest test= new UserTest ();
        test.test ();*/


    }

    public void interaction() {
        /*
        Пример входных пользовательских данных
         */
        RuleString RS = new RuleString(80, 30, false, true, true, true, "Test");
//        RuleString RT = new RuleString(80, 30, false, true, true, true, "New val");
        try {
            RS.buildTestData();
//            RT.buildTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
