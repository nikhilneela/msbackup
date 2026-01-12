package org.example.filter.filterdata;

import lombok.AllArgsConstructor;
import org.example.model.TimeSlot;

import java.time.LocalDateTime;

@AllArgsConstructor
public class TimeSlotFilterData implements IFilterData {
    private final TimeSlot slot;
}
