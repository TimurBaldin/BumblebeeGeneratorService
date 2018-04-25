package TipDataRule;
import Rules.ValidationRules;
import java.util.HashMap;
public class RuleString implements ValidationRules {
    private int Len;
    private int INCREASE_QUANTITY;
    private boolean Low;
    private boolean CAPITAL;
    private boolean ESC_SPECIAL;
    private boolean SPECIAL;
    public  RuleString(int Len,int INCREASE_QUANTITY,boolean Low,boolean CAPITAL, boolean ESC_SPECIAL,boolean SPECIAL) {
this.Len=Len;
this.INCREASE_QUANTITY=INCREASE_QUANTITY;
this.Low=Low;
this.CAPITAL=CAPITAL;
this.ESC_SPECIAL=ESC_SPECIAL;
this.SPECIAL=SPECIAL;
    }
    private boolean checkRule() {
        if((Len>0 && INCREASE_QUANTITY>0)){
            if(!Low && !CAPITAL){return false;}else{return true;}
        }else {return false;}
    }
    public void buildTestData() throws Exception{
if(!checkRule ()){throw new Exception ("invalid input");}
    }
}
