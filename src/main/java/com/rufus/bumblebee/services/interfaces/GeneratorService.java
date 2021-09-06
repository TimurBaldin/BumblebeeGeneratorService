package com.rufus.bumblebee.services.interfaces;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public interface GeneratorService<T> {

    void initGenerators(T request, SseEmitter emitter) throws IOException;

}
