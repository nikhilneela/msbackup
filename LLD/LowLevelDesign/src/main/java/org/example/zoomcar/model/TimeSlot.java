package org.example.zoomcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class TimeSlot {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public TimeSlot(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public boolean doesOverlap(final TimeSlot slot) {
        return !(slot.to.isBefore(this.from) || slot.from.isAfter(this.to));
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
