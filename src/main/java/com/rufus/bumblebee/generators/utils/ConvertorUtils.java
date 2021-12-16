package com.rufus.bumblebee.generators.utils;

public class ConvertorUtils {

    private ConvertorUtils() {
    }

    public static int convertStringToInt(String parameter) {
        return Integer.parseInt(parameter);
    }

    public static boolean convertStringToBoolean(String parameter) {
        return Boolean.parseBoolean(parameter);
    }

}
