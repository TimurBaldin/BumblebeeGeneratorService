package com.rufus.bumblebee.generators;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class GeneratorInformation {
    String generatorName;
    Map<String, String> values;
}
