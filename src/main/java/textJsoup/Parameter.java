package textJsoup;

import java.util.List;

public class Parameter {
    private String key;
    private List<?> values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<?> getValues() {
        return values;
    }

    public void setValues(List<?> values) {
        this.values = values;
    }
}
