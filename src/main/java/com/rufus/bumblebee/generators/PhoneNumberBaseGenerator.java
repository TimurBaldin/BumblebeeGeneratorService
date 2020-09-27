package com.rufus.bumblebee.generators;
import com.rufus.bumblebee.datatype.TypeTestData;
import com.rufus.bumblebee.exeptions.TransferException;
import com.rufus.bumblebee.generators.annotation.GeneratorDescription;
import com.rufus.bumblebee.generators.annotation.GeneratorParameter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@GeneratorDescription(
        generatorName = "PhoneNumberGenerator",
        generatorClass = PhoneNumberBaseGenerator.class,
        description = "Generator for create random telephone numbers"
)
@Component
@Scope("prototype")
public class PhoneNumberBaseGenerator implements BaseGenerator {

    private List<TypeTestData> values = new LinkedList<>();
    private String[] countryCodes, cityCodes;

    @GeneratorParameter(name = "delimiter", InClass = String.class)
    public String delimiter;
    @GeneratorParameter(name = "containerRef", InClass = Long.class)
    public Long containerRef;
    @GeneratorParameter(name = "len", InClass = Integer.class)
    public Integer len;
    @GeneratorParameter(name = "count", InClass = Integer.class)
    public Integer count;


    /**
     * Вызов методов "генераторов"
     */
    @Override
    public void construct() {
        StringBuilder buffer = new StringBuilder();
    }

    /*
    private void generateCountryCodes(String... countryCodes, StringBuilder buffer) {
     */
       /* if (countryCodes.length != 0) {
            for (String code : countryCodes) {
                buffer.append((char) code);
            }
        }

        */
    // }


    /**
     * Передача значений для записи в БД
     *
     * @throws TransferException ошибка при передачи данных
     */
    @Override
    public List<TypeTestData> getTestData() throws TransferException {
        return values;
    }

    @Override
    public String toString() {
        return "PhoneNumberBaseGenerator{" +
                "values=" + values +
                ", countryCodes=" + Arrays.toString(countryCodes) +
                ", cityCodes=" + Arrays.toString(cityCodes) +
                ", delimiter='" + delimiter + '\'' +
                ", containerRef=" + containerRef +
                ", len=" + len +
                ", count=" + count +
                '}';
    }
}
