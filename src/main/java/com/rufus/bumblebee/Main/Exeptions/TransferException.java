package com.rufus.bumblebee.Main.Exeptions;

/**
 * Class : Исключение передачи данных в объект "колонку"
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public class TransferException extends Exception {
    public TransferException(String message){
        super(message);
        }
}
