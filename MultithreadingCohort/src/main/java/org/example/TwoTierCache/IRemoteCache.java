package org.example.TwoTierCache;

public interface IRemoteCache {
    String get(String key);
    void put(String key, String value);
}
