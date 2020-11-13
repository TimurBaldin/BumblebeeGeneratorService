package com.rufus.bumblebee.generators;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class GeneratorInformation {
    String generatorName;
    Map<String, String> values;
}
