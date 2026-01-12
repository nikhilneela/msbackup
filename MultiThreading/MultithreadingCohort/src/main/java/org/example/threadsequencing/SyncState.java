package org.example.threadsequencing;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SyncState {
    private final ConcurrentHashMap<String, Boolean> map;

    public SyncState(final ConcurrentHashMap<String, Boolean> map) {
        this.map = map;
    }

    public boolean canPrint(final String key) {
        return this.map.get(key);
    }

    public void setPrint(String message, boolean b) {
        this.map.put(message, b);
    }
}
