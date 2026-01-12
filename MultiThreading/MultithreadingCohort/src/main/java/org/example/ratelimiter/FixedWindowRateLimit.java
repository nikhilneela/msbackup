package org.example.ratelimiter;

import lombok.AllArgsConstructor;

import java.util.HashMap;

public class FixedWindowRateLimit {
    @AllArgsConstructor
    class Tuple {
        private int count;
        private int lastMinute;
    }

    private final HashMap<String, Tuple> requestsPerKey;
    private final int MAX_ALLOWED = 100;
    public FixedWindowRateLimit() {
        requestsPerKey = new HashMap<>();
    }

    public boolean isRequestAllowed(final String key) {
        if (!requestsPerKey.containsKey(key)) {
            requestsPerKey.put(key, new Tuple(1, getCurrentMinute()));
            return true;
        }
        Tuple tuple = requestsPerKey.get(key);
        if (tuple.lastMinute != getCurrentMinute()) {
            tuple.count = 1;
            tuple.lastMinute = getCurrentMinute();
            return true;
        } else if (tuple.count < MAX_ALLOWED) {
            tuple.count++;
            return true;
        }
        return false;
    }

    private int getCurrentMinute() {
        return (int)System.currentTimeMillis() / 1000 / 60;
    }
}
