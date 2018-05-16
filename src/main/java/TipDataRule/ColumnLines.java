package TipDataRule;
import LineGenerator.StringBoundaryValues;
import LineGenerator.StringSpecialValues;
import Rules.*;
import Tools.*;
import java.util.ArrayList;
import java.util.List;
public class ColumnLines implements FactoryRules {
    private String COLUMN_NAME;
    private List<String> values = new ArrayList<String> ( );
    private Rules<List<String>>SpecialValues;
    private Rules<List<String>>BoundaryValues;
    public ColumnLines(String COLUMN_NAME){
        this.COLUMN_NAME=COLUMN_NAME;
    }
    public void setSpecialValues(StringSpecialValues stringSpecialValues) throws Exception{
        if(stringSpecialValues==null){
            throw new Exception ("Object StringSpecialValues not NULL");
        }
        this.SpecialValues=stringSpecialValues;
        values.addAll(SpecialValues.returnValue());
    }
    public void setBoundaryValues(StringBoundaryValues stringBoundaryValues)throws Exception{
        if(stringBoundaryValues==null){
            throw new Exception ("Object StringBoundaryValues not NULL");
        }
        this.BoundaryValues=stringBoundaryValues;
        values.addAll(BoundaryValues.returnValue());
    }
    private boolean checkRule() {
        if(COLUMN_NAME==null || values.size()==0){
            return false;
        }else{
            return true;
        }
    }
    public void writeToTable() throws Exception{
        if(!checkRule()){
            throw new Exception ("invalid input");
        }
       TestLinesRepository tool = new TestLinesRepository (values, COLUMN_NAME);
      tool.create ( );
    }
}
