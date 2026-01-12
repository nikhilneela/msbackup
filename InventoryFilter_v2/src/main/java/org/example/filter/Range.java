package org.example.filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Range<T>  {
    private final T min;
    private final T max;
}
