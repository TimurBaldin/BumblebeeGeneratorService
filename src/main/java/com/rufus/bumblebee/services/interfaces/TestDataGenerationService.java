package com.rufus.bumblebee.services.interfaces;

import com.rufus.bumblebee.generators.BaseGenerator;
import com.rufus.bumblebee.repository.tables.Container;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

public interface TestDataGenerationService {

    void generateTestData(List<BaseGenerator> generators, Container container, SseEmitter emitter) throws IOException;

}
