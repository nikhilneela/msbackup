package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;
}
