package org.example.TwoTierCache;

import lombok.AllArgsConstructor;
import org.example.TwoTierCache.lockmanager.IDistributedLockManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class TwoTierCache {
    private final ILocalCache localCache;
    private final IRemoteCache remoteCache;
    private final IDataStore dataStore;
    private final Map<String, Object> keyLocksMap = new ConcurrentHashMap<>();
    private final IDistributedLockManager lockManager;

    public String get(final String key) {
        //Check if the item is present in local cache
        //If present, return the value
        //If not present (in local cache), check in the remote cache
        //If present update local cache and return the value
        //If not present (in remote cache), fetch the value from source
        //update remote cache, update local cache, return the value

        String localValue = localCache.get(key);
        if (localValue != null) {
            return localValue;
        }

        Object keyLock = keyLocksMap.computeIfAbsent(key, (k) -> new Object());

        synchronized (keyLock) {
            //double-checked locking to ensure only one thread will update local cache and only one thread calls
            //remote cache. This is to ensure we do not bombard remote cache when the local cache miss
            if (localCache.get(key) != null) {
                String remoteValue = remoteCache.get(key);
                if (remoteValue != null) {
                    localCache.put(key, remoteValue);
                    return remoteValue;
                }
                return refreshRemoteCache(key);
            }
            return localCache.get(key);
        }
    }

    public String refreshRemoteCache(String key) {
        boolean acquired = lockManager.acquireLock(key);
        if (acquired) {
            try {
                if (remoteCache.get(key) != null) {
                    return remoteCache.get(key);
                }
                final String storeValue = dataStore.get(key);
                remoteCache.put(key,storeValue);
                localCache.put(key, storeValue);
                return storeValue;
            } finally {
                lockManager.releaseLock(key);
            }
        } else {
            return refreshRemoteCache(key);
        }
    }
}
