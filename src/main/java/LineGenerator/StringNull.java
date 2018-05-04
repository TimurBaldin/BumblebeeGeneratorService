package LineGenerator;

import Rules.Rules;

public final class StringNull implements Rules<String> {
    private final String NULL = null;

    public String returnValue() {
        return NULL;
    }
}
