package com.rufus.bumblebee.exeptions;

/**
 * Class : Исключение передачи данных в объект "колонку"
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class TransferException extends Exception {

    public TransferException(String message) {
        super(message);
    }

}
