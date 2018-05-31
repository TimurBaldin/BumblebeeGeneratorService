package Rules;

import java.util.List;

public interface FactoryRules <T> {
    void setValues(List<T> values);
    List<T> getValues();
}
