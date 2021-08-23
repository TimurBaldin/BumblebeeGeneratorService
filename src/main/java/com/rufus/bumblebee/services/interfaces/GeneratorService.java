package com.rufus.bumblebee.services.interfaces;

public interface GeneratorService<T, R> {

    R initGenerators(T request) throws Exception;

}
