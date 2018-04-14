package TipDataRule;
import java.util.HashMap;
public interface ValidationRules {
    void loadRule(HashMap<String,?> input_rule);
    boolean checkRule();
}
