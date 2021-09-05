package com.rufus.bumblebee.controllers;

import com.rufus.bumblebee.controllers.requests.GeneratorsRequest;
import com.rufus.bumblebee.services.interfaces.GeneratorService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RestController
@RequestMapping(path = "/emi")
@Api(value = "Controller for emi", tags = {"Controller for emi"})
public class ResponseBodyEmitterController {

    private final GeneratorService<GeneratorsRequest> generatorService;

    public ResponseBodyEmitterController(GeneratorService<GeneratorsRequest> generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping(path = "/stream-sse-mvc",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSseMvc() throws Exception {
        System.out.println("@Thread streamSseMvc: "+Thread.currentThread().getId());
        SseEmitter emitter = new SseEmitter();
        GeneratorsRequest request=new GeneratorsRequest();
        request.setCuid(UUID.randomUUID().toString());
        generatorService.initGenerators(request,emitter);
        return emitter;
    }
}
