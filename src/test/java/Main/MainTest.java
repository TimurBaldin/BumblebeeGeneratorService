package Main;
import LineGeneratorTest.*;
import org.junit.Test;

public class MainTest {
@Test
public void runStringNull() {
    new StringNullTest().run();
}
@Test
public void runStringBoundaryValues(){new StringBoundaryValuesTest().run();}

}