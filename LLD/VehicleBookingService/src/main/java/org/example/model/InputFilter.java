package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.filter.filterdata.IFilterData;

@AllArgsConstructor
@Getter
public class InputFilter {
    private final FilterType type;
    private final IFilterData filterData;
}
