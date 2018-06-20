<<<<<<< HEAD
package LineGeneratorTest;
import com.rufus.bumblebee.Main.LineGenerator.StringBoundaryValues;
import TestObjects.ExResBorderRange;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class StringBoundaryValuesTest {
private StringBoundaryValues test_obj;
private ExResBorderRange ExRestest_1;
private ExResBorderRange ExRestest_2;
private List<String> values_test = new ArrayList<String>();

public void run() {
    precondition();
}
@Before
public void precondition() {
    ApplicationContext context =
            new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\test\\resources\\config.xml");
    ApplicationContext testcontext =
            new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\test\\resources\\bean_config_test_objects.xml");
    ExRestest_1 = (ExResBorderRange) testcontext.getBean("Test_1_LOW");
    ExRestest_2 = (ExResBorderRange) testcontext.getBean("Test_1_CAP");
    test_obj = (StringBoundaryValues) context.getBean("TestObj_1");
    try {
        //values_test.addAll(test_obj.construct());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
/**
 * @method : test_case_1
 * @condition : values_test(char_id>=97 AND char_id<=122)
 */
@Test
public void test_case_1() {
for(int i=1;i<=values_test.size()-1;i++){
    assertEquals(values_test.get(i).charAt(0)>=ExRestest_1.getSTART_ID_VALUE(),true);
    assertEquals(values_test.get(i).charAt(0)<=ExRestest_1.getEND_ID_VALUE(),true);
}
assertEquals(values_test.size(),200);
}
}
=======
//package LineGeneratorTest;
//
//import io.LineGenerator.StringBoundaryValues;
//import TestObjects.ExResBorderRange;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestConfiguration.class})
//
//public class StringBoundaryValuesTest {
//
//    private StringBoundaryValues test_obj;
//    private ExResBorderRange ExRestest_1;
//    private ExResBorderRange ExRestest_2;
//    private List<String> values_test = new ArrayList<String>();
//
//    public void run() {
//        precondition();
//    }
//
//    @Before
//    public void precondition() {
//        ApplicationContext context =
//                new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\test\\resources\\config.xml");
//        ApplicationContext testcontext =
//                new FileSystemXmlApplicationContext("C:\\Users\\Timur\\Documents\\Data Generator\\src\\test\\resources\\bean_config_test_objects.xml");
//        ExRestest_1 = (ExResBorderRange) testcontext.getBean("Test_1_LOW");
//        ExRestest_2 = (ExResBorderRange) testcontext.getBean("Test_1_CAP");
//        test_obj = (StringBoundaryValues) context.getBean("TestObj_1");
//        try {
//            //values_test.addAll(test_obj.construct());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @method : test_case_1
//     * @condition : values_test(char_id>=97 AND char_id<=122)
//     */
//    @Test
//    public void test_case_1() {
//        for (int i = 1; i <= values_test.size() - 1; i++) {
//            assertEquals(values_test.get(i).charAt(0) >= ExRestest_1.getSTART_ID_VALUE(), true);
//            assertEquals(values_test.get(i).charAt(0) <= ExRestest_1.getEND_ID_VALUE(), true);
//        }
//        assertEquals(values_test.size(), 200);
//    }
//}
>>>>>>> 922ab94a01e7a015e204749a36624e81940aefd0
