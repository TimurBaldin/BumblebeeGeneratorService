package com.rufus.bumblebee.generators.factories;

import com.rufus.bumblebee.generators.DataGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface DataGeneratorFactory {
    DataGenerator createGenerator(Map<String, String> values, DataGenerator generator) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
