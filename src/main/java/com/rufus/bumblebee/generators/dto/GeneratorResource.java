package com.rufus.bumblebee.generators.dto;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;

public class GeneratorResource extends SoftReference<Map<String, List<String>>> {

    public GeneratorResource(Map<String, List<String>> data, ReferenceQueue<Map<String, List<String>>> queue) {
        super(data, queue);
    }

}
