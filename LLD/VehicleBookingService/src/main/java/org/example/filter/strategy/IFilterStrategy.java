package org.example.filter.strategy;

import org.example.model.InputFilter;
import org.example.model.Vehicle;

import java.util.List;

public interface IFilterStrategy {
    boolean doesSupport(final InputFilter filter);
    List<Vehicle> apply(final InputFilter filter, final List<Vehicle> vehicles);
    InputFilter parse(final String json);
}
