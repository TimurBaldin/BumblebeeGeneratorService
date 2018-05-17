package LineGeneratorTest;
import LineGenerator.StringNull;
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
