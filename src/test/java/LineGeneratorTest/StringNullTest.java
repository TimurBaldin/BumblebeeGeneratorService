<<<<<<< HEAD
package LineGeneratorTest;
import com.rufus.bumblebee.Main.LineGenerator.StringNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class StringNullTest {
StringNull test_null;
public void run() {
    precondition();
    Test_Case_1();
    destructor();
}
@Before
private void precondition() {
    test_null = new StringNull();
}
@Test
private void Test_Case_1() {
    assertNull(test_null.returnValue());
}
@After
private void destructor() {
    test_null = null;
}
}
=======
//package LineGeneratorTest;
//
//import io.LineGenerator.StringNull;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertNull;
//
//public class StringNullTest {
//    StringNull test_null;
//
//    public void run() {
//        precondition();
//        Test_Case_1();
//        destructor();
//    }
//
//    @Before
//    private void precondition() {
//        test_null = new StringNull();
//    }
//
//    @Test
//    private void Test_Case_1() {
//        assertNull(test_null.returnValue());
//    }
//
//    @After
//    private void destructor() {
//        test_null = null;
//    }
//}
>>>>>>> 922ab94a01e7a015e204749a36624e81940aefd0
