package filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StringFilterValue implements IFilterValue<String>{
    private final String value;
    @Override
    public String getValue() {
        return value;
    }
}
