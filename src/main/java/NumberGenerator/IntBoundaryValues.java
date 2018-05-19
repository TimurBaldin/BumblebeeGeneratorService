package NumberGenerator;

import Rules.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntBoundaryValues implements Rules<List<Long>> {
private Long MaxInt;
private Long MinInt;
private Integer QUANTITY;
private final Integer QUANTITY_BOUNDARY_TEST = 6;
private Long DEFAULT_VALUE;
private List<Long> values = new ArrayList<Long>();

public IntBoundaryValues(Long BoundaryIntEnd, Long BoundaryIntStart, Integer QUANTITY) {
    this.MaxInt = BoundaryIntEnd;
    this.MinInt = BoundaryIntStart;
    this.QUANTITY = QUANTITY;
}

@Override
public List<Long> returnValue() throws Exception {
    if ((MaxInt < MinInt) || (QUANTITY < QUANTITY_BOUNDARY_TEST)) {
        throw new Exception("Invalid input conditions");
    }
    values.addAll(buildBoundary());
    values.addAll(buildTestNum());
    return values;
}
public void setDEFAULT_VALUE(Long DEFAULT_VALUE){
    this.DEFAULT_VALUE=DEFAULT_VALUE;
}
private Long buildRandNum() {
    Long Num_1;
    if (MinInt >= 0) {Num_1 = MinInt + 2;} else {Num_1 = MinInt - 1;}
    return ThreadLocalRandom.current().nextLong(Num_1, MaxInt);
}
private List<Long> buildBoundary(){
    List<Long> test_val = new ArrayList<Long>();
    for(Long j=-1L;j<=1L;j++){
        test_val.add(MinInt-j);
        test_val.add(MaxInt-j);
    }
    return test_val;
}
private List<Long>  buildTestNum(){
    List<Long> test_val = new ArrayList<Long>();
    if(QUANTITY>QUANTITY_BOUNDARY_TEST){
        if(DEFAULT_VALUE==null) {
            for (int i = 1; i <= QUANTITY-QUANTITY_BOUNDARY_TEST; i++) {
                test_val.add(buildRandNum());
            }
        }else {
            for (int i = 1; i <= QUANTITY-QUANTITY_BOUNDARY_TEST; i++) {
                test_val.add(DEFAULT_VALUE);
            }
        }
    }
    return test_val;
}
}
