package TipDataRule;
import Rules.*;
import LineGenerator.*;
import Tools.*;
import java.util.*;
public class RuleString implements ValidationRules {
    private int Len;
    private int INCREASE_QUANTITY;
    private boolean Low;
    private boolean CAPITAL;
    private boolean ESC_SPECIAL;
    private boolean SPECIAL;
    private String COLUMN_NAME;
    private List<String> values=new ArrayList<String> ();
    public  RuleString(int Len,int INCREASE_QUANTITY,boolean Low,boolean CAPITAL, boolean ESC_SPECIAL,boolean SPECIAL,String COLUMN_NAME) {
this.Len=Len;
this.INCREASE_QUANTITY=INCREASE_QUANTITY;
this.Low=Low;
this.CAPITAL=CAPITAL;
this.ESC_SPECIAL=ESC_SPECIAL;
this.SPECIAL=SPECIAL;
this.COLUMN_NAME=COLUMN_NAME;
    }
    private boolean checkRule() {
        if((Len>0 && INCREASE_QUANTITY>0)&& COLUMN_NAME!=""){
            if(!Low && !CAPITAL){return false;}else{return true;}
        }else {return false;}
    }
    public void buildTestData() throws Exception{
if(!checkRule ()){throw new Exception ("invalid input");}
if(!ESC_SPECIAL && !SPECIAL) {
    Rules<List<String>> SBV = new StringBoundaryValues (Len, INCREASE_QUANTITY, Low, CAPITAL);
    try{
        values.addAll (SBV.returnValue ());
    }catch (Exception ex){
        ex.printStackTrace ();
    }
}else{
    Rules<List<String>> SBV = new StringBoundaryValues (Len, INCREASE_QUANTITY, Low, CAPITAL);
    Rules<List<String>> SSV=new StringSpecialValues (Len,INCREASE_QUANTITY,ESC_SPECIAL,SPECIAL);
    try{
        values.addAll (SBV.returnValue ());
        values.addAll (SSV.returnValue ());
    }catch (Exception ex){
        ex.printStackTrace ();
    }
}
if(values.size ()!=0){
   WriteData ();
}else {
    throw new Exception ("Invalid test values (values==0)");
}


    }
    private void WriteData(){
       LineWriterTool writer=new LineWriterTool (values,COLUMN_NAME);
       writer.writeBD ();
       }

}
