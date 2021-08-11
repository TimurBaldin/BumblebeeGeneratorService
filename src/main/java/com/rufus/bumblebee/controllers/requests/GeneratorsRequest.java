package com.rufus.bumblebee.controllers.requests;

import com.rufus.bumblebee.generators.GeneratorInformation;
import javax.validation.constraints.NotNull;
import java.util.List;


public class GeneratorsRequest {
    @NotNull
    private String cuid;
    private List<GeneratorInformation> generatorInfo;

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public List<GeneratorInformation> getGeneratorInfo() {
        return generatorInfo;
    }

    public void setGeneratorInfo(List<GeneratorInformation> generatorInfo) {
        this.generatorInfo = generatorInfo;
    }
}
