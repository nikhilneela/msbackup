package org.example.ratelimiter;

import java.util.HashMap;

public class BasicRateLimitUsingHashMap {
    private final HashMap<String, Integer> requestsPerKey;
    private final HashMap<String, Object> lockPerKey;
    private final Integer MAX_ALLOWED = 100;

    public BasicRateLimitUsingHashMap() {
        requestsPerKey = new HashMap<>();
        lockPerKey = new HashMap<>();
    }

    public boolean isRequestAllowed(final String key) {

        if (!requestsPerKey.containsKey(key)) {
            synchronized (requestsPerKey) {
                if (!requestsPerKey.containsKey(key)) {
                    requestsPerKey.put(key, 0);
                    lockPerKey.put(key, new Object());
                }
            }
        }

        if (requestsPerKey.get(key) < MAX_ALLOWED) {
            synchronized (lockPerKey.get(key)) {
                if (requestsPerKey.get(key) < MAX_ALLOWED) {
                    requestsPerKey.put(key, requestsPerKey.get(key) + 1);
                    return true;
                }
            }
        }
        return false;
    }
}
