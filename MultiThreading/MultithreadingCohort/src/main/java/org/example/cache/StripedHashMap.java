package org.example.cache;

import java.util.*;
import java.util.concurrent.locks.*;

public class StripedHashMap<K, V> {
    private static final int NUM_LOCKS = 16;
    private final Map<K, V> map = new HashMap<>();
    private final ReentrantLock[] locks = new ReentrantLock[NUM_LOCKS];

    public StripedHashMap() {
        for (int i = 0; i < NUM_LOCKS; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    private int lockIndex(Object key) {
        return (key.hashCode() & 0x7fffffff) % NUM_LOCKS;
    }

    public V put(K key, V value) {
        int idx = lockIndex(key);
        locks[idx].lock();
        try {
            return map.put(key, value);
        } finally {
            locks[idx].unlock();
        }
    }

    public V get(K key) {
        int idx = lockIndex(key);
        locks[idx].lock();
        try {
            return map.get(key);
        } finally {
            locks[idx].unlock();
        }
    }

    public int size() {
        return map.size();
    }
}

