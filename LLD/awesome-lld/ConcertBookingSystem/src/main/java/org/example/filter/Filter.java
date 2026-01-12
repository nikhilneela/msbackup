package org.example.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.filter.parser.IFilterData;

@Getter
@AllArgsConstructor
public class Filter<T> {
    private IFilterData<T> data;
    private FilterType type;
    private FilterCondition condition;
}
