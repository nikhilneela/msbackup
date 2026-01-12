package org.example.TwoTierCache;

public interface ILocalCache {
    String get(String key);
    void put(String key, String value);
}
