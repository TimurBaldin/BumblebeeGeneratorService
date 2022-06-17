package com.rufus.bumblebee.services.generators;

import com.rufus.bumblebee.services.interfaces.InitGeneratorService;

public abstract class BaseInitGeneratorService<R> implements InitGeneratorService<R> {

    abstract void validateGeneratorsRequest(R request) throws Exception;
}
