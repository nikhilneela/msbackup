package org.example.filter.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DateRange {
    private LocalDate start;
    private LocalDate end;
}
