package com.rufus.bumblebee.services.interfaces;

import java.util.Map;

public interface GeneratorService<T> {

    Map<String, String> initGenerators(T request) throws Exception;

}
