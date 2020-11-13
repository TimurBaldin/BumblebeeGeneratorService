package com.rufus.bumblebee.controllers.requests;

import com.rufus.bumblebee.generators.GeneratorInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GeneratorsRequest extends BaseRequest {
    List<GeneratorInformation> generatorInfo;
}
