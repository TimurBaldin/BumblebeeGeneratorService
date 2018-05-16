package Logic;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Rules.Rules;
import TipDataRule.*;
import Tools.SessionUntil;

import java.util.List;

public class LogicRule {
    public static void main(String[] args) {
      LogicRule l=new LogicRule ();
        l.interaction ();
      }

        public void interaction() {
            try {

                ColumnLines columnLines=new ColumnLines("Fix");

        /*
        Пример входных пользовательских данных
         */
                StringBoundaryValues SBV = new StringBoundaryValues(100, 25, true, true);
                StringSpecialValues SSV = new StringSpecialValues(100, 35, true, true);

       columnLines.setBoundaryValues(SBV);
       columnLines.setSpecialValues(SSV);
        columnLines.writeToTable();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SessionUntil.INSTANCE.close ();
        }
        }
}
