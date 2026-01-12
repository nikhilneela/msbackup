package org.example;

import org.example.filter.IFilterValue;
import org.example.filter.Range;
import org.example.filter.RangeFilterValue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IFilterValue<Range<Integer>> filterValue = new RangeFilterValue<>(new Range<>(3, 5));
    }
}