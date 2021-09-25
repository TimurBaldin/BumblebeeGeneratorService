package com.rufus.bumblebee.services.interfaces;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface GeneratorService<T> {

    void initGenerators(T request, SseEmitter emitter) throws Exception;

}
