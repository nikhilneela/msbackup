package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Event {
    private final String id;
    private final TimeSlot slot;
    private final User owner;
    private final List<User> guests;
    private final EventType type;
    private final Location location;
}
