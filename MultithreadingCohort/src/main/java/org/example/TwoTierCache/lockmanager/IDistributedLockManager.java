package org.example.TwoTierCache.lockmanager;

public interface IDistributedLockManager {
    boolean acquireLock(final String key);
    void releaseLock(final String key);
}
