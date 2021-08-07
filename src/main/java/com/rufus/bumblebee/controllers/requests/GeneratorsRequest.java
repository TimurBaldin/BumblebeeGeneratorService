package com.rufus.bumblebee.controllers.requests;

import com.rufus.bumblebee.generators.GeneratorInformation;
import javax.validation.constraints.NotNull;
import java.util.List;


public class GeneratorsRequest {
    @NotNull
    private Long containerId;
    private List<GeneratorInformation> generatorInfo;

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public List<GeneratorInformation> getGeneratorInfo() {
        return generatorInfo;
    }

    public void setGeneratorInfo(List<GeneratorInformation> generatorInfo) {
        this.generatorInfo = generatorInfo;
    }
}
