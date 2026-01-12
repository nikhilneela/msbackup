package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
