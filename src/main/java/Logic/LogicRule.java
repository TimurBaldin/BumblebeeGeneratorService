package Logic;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Columns.*;
import NumberGenerator.IntBoundaryValues;
import Tools.SessionUntil;

public class LogicRule {
    public static void main(String[] args) {
      LogicRule l=new LogicRule ();
       // l.interaction ();
        l.test();

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
        public  void test(){
            IntBoundaryValues test=new IntBoundaryValues(10l,1l,15);

            try {
                test.returnValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
