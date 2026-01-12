package org.example.ratelimiter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;


public class SlidingWindowRateLimitUsingHashMap {
    private final ConcurrentHashMap<String, Queue<Node>> requestsPerKey;
    private final int MAX_ALLOWED = 100;

    public SlidingWindowRateLimitUsingHashMap() {
        requestsPerKey = new ConcurrentHashMap<>();
    }

    public boolean isRequestAllowed(final String key) {
        requestsPerKey.putIfAbsent(key, new LinkedList<>());

        Queue<Node> window = requestsPerKey.get(key);

        synchronized (window) {
            while (!window.isEmpty() && System.currentTimeMillis() - window.peek().getTimeInMills() > 1*60*1000) {
                window.poll();
            }
        }

        if (window.size() < MAX_ALLOWED) {
            synchronized (window) {
                if (window.size() < MAX_ALLOWED) {
                    window.add(new Node(key, System.currentTimeMillis()));
                    return true;
                }
            }
        }
        return false;
    }
}
