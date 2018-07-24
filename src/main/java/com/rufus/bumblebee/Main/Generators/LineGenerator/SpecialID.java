package com.rufus.bumblebee.Main.Generators.LineGenerator;

public enum SpecialID {
    KEY_ID;
    private  final int MIN_ID_ESC = 1;
    private  final int MAX_ID_ESC = 31;
    //Escape symbols
    private  final int MIN_ID_SPECIAL_1 = 33;
    private  final int MAX_ID_SPECIAL_1 = 64;
    //Other symbols 1
    private final int MIN_ID_SPECIAL_2 = 128;
    private final int MAX_ID_SPECIAL_2 = 191;

    public int getMIN_ID_ESC() {
        return MIN_ID_ESC;
    }

    public int getMAX_ID_ESC() {
        return MAX_ID_ESC;
    }

    public int getMIN_ID_SPECIAL_1() {
        return MIN_ID_SPECIAL_1;
    }

    public int getMAX_ID_SPECIAL_1() {
        return MAX_ID_SPECIAL_1;
    }

    public int getMIN_ID_SPECIAL_2() {
        return MIN_ID_SPECIAL_2;
    }

    public int getMAX_ID_SPECIAL_2() {
        return MAX_ID_SPECIAL_2;
    }
}
