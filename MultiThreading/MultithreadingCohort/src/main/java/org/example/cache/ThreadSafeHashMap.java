package org.example.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeHashMap {
    private final Map<String, Integer> map = new HashMap<>();
    private final int HASH_RANGE = 10009;
    private final ReentrantLock[] locks = new ReentrantLock[HASH_RANGE];

    public ThreadSafeHashMap() {
        for (int i = 0; i < HASH_RANGE; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public void put(String key, Integer val) {
        ReentrantLock lock = getLock(key);
        lock.lock();
        map.put(key, val);
        lock.unlock();
    }

    public Integer get(String key) {
        return map.get(key);
    }

    ReentrantLock getLock(final String key) {
        return locks[key.hashCode() % HASH_RANGE];
    }

    public int size() {
        return map.size();
    }
}
