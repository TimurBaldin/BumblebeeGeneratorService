package NumberGenerator;

import Rules.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntFullRange implements Rules {
private Long MinIntVal;
private Long MaxIntVal;
private List<Long> values = new ArrayList<Long>();

public IntFullRange(Long MaxIntVal, Long MinIntVal) {
    this.MinIntVal = MinIntVal;
    this.MaxIntVal = MaxIntVal;
}

@Override
public void construct() throws Exception {
    if (checkRule()) {
        throw new Exception("Your choice is not right. Try again");
    }
    for (int i = 1; i <= MaxIntVal; i++) {
        values.add(buildRandNum());
    }
    transfer();
}

@Override
public void transfer() throws Exception {

}

private boolean checkRule() {
    if ((MaxIntVal < MinIntVal) || (Math.abs(MaxIntVal - MinIntVal) == 0)) {
        return true;
    } else {
        return false;
    }
}

private Long buildRandNum() {
    return ThreadLocalRandom.current().nextLong(MinIntVal, MaxIntVal + 1);
}

}