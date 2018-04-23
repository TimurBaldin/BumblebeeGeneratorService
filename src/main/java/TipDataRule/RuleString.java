package TipDataRule;
import Rules.StringBoundaryValues;
import Rules.StringSpecialValues;
import java.util.HashMap;
public class RuleString implements ValidationRules {
    private int Len;
    private int SPECIAL_LEN;
    private int INCREASE_QUANTITY;
    private boolean Low;
    private boolean CAPITAL;
    private Boolean ESC_SPECIAL;
    private Boolean SPECIAL;
    @Override
    public void loadRule(HashMap<String, ?> input_rule) {

    }
    private boolean checkRule() {
        return false;
    }
    private void buildTestData(){
        StringBoundaryValues stringBoundaryValues=
                new StringBoundaryValues (Len,INCREASE_QUANTITY,Low,CAPITAL);
        StringSpecialValues stringSpecialValues=
                new StringSpecialValues (SPECIAL_LEN,INCREASE_QUANTITY, ESC_SPECIAL,SPECIAL);
        try {
            stringBoundaryValues.returnValue ();
            stringSpecialValues.returnValue ();

        }catch (Exception ex){

        }
    }
}
