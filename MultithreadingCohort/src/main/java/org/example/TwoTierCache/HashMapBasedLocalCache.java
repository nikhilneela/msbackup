package org.example.TwoTierCache;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class HashMapBasedLocalCache implements ILocalCache {
    private final long ttlInMills;
    private final Map<String, CacheItem> cacheStore = new ConcurrentHashMap<>();

    @Override
    public String get(final String key) {
        CacheItem item = cacheStore.get(key);
        if (item != null && !item.isExpired()) {
            return item.getValue();
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        CacheItem cacheItem = new CacheItem(value, System.currentTimeMillis() + ttlInMills);
        cacheStore.put(key, cacheItem);
    }
}
