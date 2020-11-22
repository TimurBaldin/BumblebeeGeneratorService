package com.rufus.bumblebee.generators;

import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import com.rufus.bumblebee.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@GeneratorDescription(
        generatorName = "PhoneNumberGenerator",
        generatorClass = PhoneNumberBaseGenerator.class,
        description = "Generator for create random telephone numbers"
)
@Component
@Scope("prototype")
public class PhoneNumberBaseGenerator implements BaseGenerator {

    @Autowired
    private TestDataRepository repository;

    private List<TypeTestData> values = new LinkedList<>();
    private String[] countryCodes, cityCodes;

    @GeneratorParameter(name = "maxLen", InClass = Integer.class)
    public Integer maxLen;
    @GeneratorParameter(name = "maxLen", InClass = Integer.class)
    public Integer minLen;
    @GeneratorParameter(name = "count", InClass = Integer.class)
    public Integer count;

    List<Integer> codes = repository.getCountryCodes();

    /**
     * Вызов методов "генераторов"
     */
    @Override
    public void construct() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            buildCountryCodes(buffer);
        }

    }

    private void buildCountryCodes(StringBuilder buffer) {
        buffer.append("+" + codes.get(ThreadLocalRandom.current().nextInt(0, codes.size() - 1)).toString());
    }

    private void buildOperatorCode(StringBuilder buffer) {

    }


    @Override
    public List<TypeTestData> getTestData() {
        return values;
    }
}
