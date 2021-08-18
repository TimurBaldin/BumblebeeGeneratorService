package com.rufus.bumblebee.services.interfaces;

public interface GeneratorService<T, R> {

    R addGenerators(T request) throws Exception;

}
