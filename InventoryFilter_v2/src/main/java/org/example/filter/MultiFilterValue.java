package org.example.filter;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MultiFilterValue implements IFilterValue<List<String>> {
    private final List<String> values;

    @Override
    public List<String> getValue() {
        return values;
    }
}
