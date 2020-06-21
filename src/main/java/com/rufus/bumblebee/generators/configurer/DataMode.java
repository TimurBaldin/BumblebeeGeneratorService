package com.rufus.bumblebee.generators.configurer;

public enum DataMode {

    STRING("STRING"),
    NUMBER("NUMBER");
    String key;

    DataMode(String key) {
        this.key = key;
    }

    public String getMode() {
        return key;
    }
}
