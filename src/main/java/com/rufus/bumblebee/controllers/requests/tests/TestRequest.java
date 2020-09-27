package com.rufus.bumblebee.controllers.requests.tests;

import com.rufus.bumblebee.generators.GeneratorInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
public class TestRequest extends BaseRequest {
    ArrayList<GeneratorInformation> generatorInformations;
}
