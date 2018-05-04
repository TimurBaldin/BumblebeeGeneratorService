package LineGenerator;
import Rules.Rules;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class StringSpecialValues implements Rules<List<String>> {
    private final int MIN_ID_ESC=1;
    private final int MAX_ID_ESC=31;
    private final int ESC_LEN=30 ;
    //Escape symbols
    private final int MIN_ID_SPECIAL_1=33;
    private final int MAX_ID_SPECIAL_1=64;
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2=128;
    private final int MAX_ID_SPECIAL_2=191;
    //Other symbols 2
    private Integer SPECIAL_LEN ;
    private Boolean ESC_SPECIAL;
    private Boolean SPECIAL;
    private  int INCREASE_QUANTITY;
    private List<String> values=new ArrayList<String>();
    public StringSpecialValues(Integer SPECIAL_LEN,int INCREASE_QUANTITY,boolean ESC_SPECIAL,boolean SPECIAL){
        this.SPECIAL_LEN=SPECIAL_LEN;
        this.ESC_SPECIAL=ESC_SPECIAL;
        this.SPECIAL=SPECIAL;
        this.INCREASE_QUANTITY=INCREASE_QUANTITY;
        }
    @Override
    public List<String> returnValue() throws Exception {
        if((!ESC_SPECIAL && !SPECIAL) || SPECIAL_LEN<=0 || INCREASE_QUANTITY<=0) throw new Exception ("Your choice is not right. Try again");
        if(ESC_SPECIAL && SPECIAL){
            values.add (stringEsc ());
            for(Integer i=1;i<=SPECIAL_LEN+INCREASE_QUANTITY;i++){
                values.add (stringSpecial(i));
            }
            return values;
        }
        if(SPECIAL){
            for(Integer i=1;i<=SPECIAL_LEN+INCREASE_QUANTITY;i++){
                values.add (stringSpecial(i));
            }
            return values;
        }else {
            values.add (stringEsc ());
            return values;
        }
    }
    private String stringEsc(){
        int id=0;
        StringBuilder bufer=new StringBuilder();
        for(int i=MIN_ID_ESC;i<=MAX_ID_ESC;i++){
            char symbol=(char)i;
            id=-1;
            bufer.append (symbol);
        }
        return bufer.toString ();
    }
    private String stringSpecial(int val){
        int id=0;
        StringBuilder bufer=new StringBuilder();
        for(Integer i=1;i<=val;i++){
            if(i%2==0) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_SPECIAL_1, MAX_ID_SPECIAL_1+1);
            }else{
                id =ThreadLocalRandom.current().nextInt(MIN_ID_SPECIAL_2, MAX_ID_SPECIAL_2+1);
            }
            char symbol=(char)id;
            id=-1;
            bufer.append (symbol);
        }
        return bufer.toString ();
    }

}
