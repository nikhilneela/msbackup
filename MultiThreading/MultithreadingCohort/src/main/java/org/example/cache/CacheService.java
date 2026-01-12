package org.example.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheService {
    private final Map<String, CacheItem> cacheItemMap = new HashMap<>();

    public void put(String key, String value, long ttlInMills) {
        /*
         * Why is this not thread-safe ?
         * - If multiple PUTs come for the same key, we are good in the sense that the last value will get applied
         * - If multiple PUTs come for different keys, and they hash to the same bucket, we will land on the same list (chain)
         * - If we land on the same chain, two threads will try to operate on the same chain, resulting in corrupting the chain (unexpected results)
         * - See UnsafeHashMapDemo
         * - There are multiple ways to make PUT thread safe
         * - Option1, use Double Checked locking pattern
         *
         *
         *
         */
        cacheItemMap.put(key, new CacheItem(value, ttlInMills));
    }

    public String get(String key) {
        if (!cacheItemMap.containsKey(key)) {
            return null;
        }
        CacheItem cacheItem = cacheItemMap.get(key);
        if (cacheItem.isExpired()) {
            cacheItemMap.remove(key);
            return null;
        }
        return cacheItem.getValue();
    }
}
