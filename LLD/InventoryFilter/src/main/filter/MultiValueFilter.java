package filter;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MultiValueFilter implements IFilterValue<List<String>>{
    private final List<String> values;
    @Override
    public List<String> getValue() {
        return values;
    }
}
