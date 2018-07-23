package com.rufus.bumblebee.Main.Exeptions;

import com.rufus.bumblebee.Main.Rules.Rule;

/**
 * Class : Исключение передачи данных в объект "колонку"
 * @version : 0.0.1
 * @author : Baldin Timur
 * @see Rule#transfer()
 */
public class TransferException extends Exception {
    public TransferException(String message){
        super(message);
        }
}
