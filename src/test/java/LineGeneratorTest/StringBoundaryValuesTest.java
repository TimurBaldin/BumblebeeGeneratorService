package LineGeneratorTest;

import LineGenerator.StringBoundaryValues;
import LineGenerator.StringNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StringBoundaryValuesTest {
StringBoundaryValues test_obj;
private List<String> values_test = new ArrayList<String>();
public void run(){
    precondition();
}
@Before
private void precondition() {
    ApplicationContext context =
            new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\test\\resources\\bean_test_config.xml");
    test_obj = (StringBoundaryValues) context.getBean("TestObj_1");
    try {
        values_test.addAll(test_obj.returnValue());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
