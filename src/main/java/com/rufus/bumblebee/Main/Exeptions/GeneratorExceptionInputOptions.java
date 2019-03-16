package com.rufus.bumblebee.Main.Exeptions;

/**
 * Class : Некорректные входных данных в генераторе
 *
 * @author : Baldin Timur
 * @version : 0.0.1
 */
public class GeneratorExceptionInputOptions extends Exception {

    private String[] parameters;

    /**
     * @return : Входные параметры вызвавшие исключение
     */
    public String[] getParameters() {
        return parameters;
    }

    public GeneratorExceptionInputOptions(String message, String... ex) {
        super(message);
        this.parameters = ex;
    }

}
