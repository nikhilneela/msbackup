package org.example.filter.filterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.InputFilter;

@AllArgsConstructor
@Getter
public class AndFilterData implements IFilterData {
    private final InputFilter left;
    private final InputFilter right;
}
