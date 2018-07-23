package com.rufus.bumblebee.Main.Exeptions;

import com.rufus.bumblebee.Main.Rules.Rule;

/**
 * Class : Некорректные входных данных в генераторе
 * @version : 0.0.1
 * @author : Baldin Timur
 * @see Rule#construct()
 */
public class GeneratorExceptionInputOptions extends Exception {
    private String [] parameters;
    /**
     * @return  : Входные параметры вызвавшие исключение
     */
    public String [] getParameters(){return parameters;}
    public GeneratorExceptionInputOptions(String message,String ...ex){
super(message);
this.parameters=ex;
    }
}
