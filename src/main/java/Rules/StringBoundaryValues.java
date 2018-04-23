package Rules;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class StringBoundaryValues implements Rules<List<String>> {
    private Integer len;
    private Boolean Low;
    private Boolean Cap;
    private final int MIN_ID_CAPITALS=65;
    private final int MAX_ID_CAPITALS=90;
    private final int MIN_ID_LOWERCASE=97;
    private final int MAX_ID_LOWERCASE=122;
    private  int INCREASE_QUANTITY;
    private List<String> values=new ArrayList<String>();
    public StringBoundaryValues(int Len,int INCREASE_QUANTITY,boolean Low,boolean Cap){
        this.len=Len;
        this.Low=Low;
        this.Cap=Cap;
        this.INCREASE_QUANTITY=INCREASE_QUANTITY;
    }
    @Override
    public List<String> returnValue()throws Exception{
if((!Low && !Cap) || len<=0 || INCREASE_QUANTITY<=0) throw new Exception ("Your choice is not right. Try again");
values.add (new StringNull ().returnValue ()); //Add null value
if(Low && Cap){
    for(Integer i=1;i<=len+INCREASE_QUANTITY;i++){
        values.add (stringBuildLowCap (i));
    }
    return values;
}
if(Low){
    for(Integer i=1;i<=len+INCREASE_QUANTITY;i++){
        values.add (stringBuildLow (i));
    }
    return values;
}else{
    for(Integer i=1;i<=len+INCREASE_QUANTITY;i++){
        values.add (stringBuildCap (i));
    }
    return values;
}
       }
    private String stringBuildLowCap(int val){
        int id=0;
        String bufer="";
        for(Integer i=1;i<=val;i++){
            if(i%2==0) {
                id = ThreadLocalRandom.current().nextInt( MIN_ID_CAPITALS, MAX_ID_CAPITALS+1);
            }else{
                id =ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE+1);
            }
            Character symbol=(char)id;
            id=-1;
            bufer+=symbol;
        }
        return bufer;
    }
    private String stringBuildLow(int val){
        int id=0;
        String bufer="";
        for(Integer i=1;i<=val;i++){
            id =ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE+1);
            Character symbol=(char)id;
            id=-1;
            bufer+=symbol;
        }
        return bufer;
    }
    private String stringBuildCap(int val){
        int id=0;
        String bufer="";
        for(Integer i=1;i<=val;i++){
            id = ThreadLocalRandom.current().nextInt( MIN_ID_CAPITALS, MAX_ID_CAPITALS+1);
            Character symbol=(char)id;
            id=-1;
            bufer+=symbol;
        }
        return bufer;
    }
}
