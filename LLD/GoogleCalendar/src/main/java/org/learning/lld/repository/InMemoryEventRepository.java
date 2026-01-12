package org.learning.lld.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.model.Event;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class InMemoryEventRepository implements IEventRepository {
    private final Map<String, Event> userEvents;

    public InMemoryEventRepository() {
        this.userEvents = new HashMap<>();
    }

    @Override
    public void save(@NonNull Event event) {
        this.userEvents.put(event.getOwner().getId(), event);
    }
}
