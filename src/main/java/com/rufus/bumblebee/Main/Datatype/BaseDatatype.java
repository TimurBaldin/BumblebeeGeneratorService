package com.rufus.bumblebee.Main.Datatype;

import com.rufus.bumblebee.Main.Rules.TypeTestData;

public class BaseDatatype implements TypeTestData<String,String> {
    private String bufer;
    private String key;
public BaseDatatype(String bufer, String key){
    this.bufer=bufer;
    this.key=key;
}

    @Override
    public String getKey() {
        return key;
    }
    @Override
    public String getValue(){
        return bufer;
    }

}
