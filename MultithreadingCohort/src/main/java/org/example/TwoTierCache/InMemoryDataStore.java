package org.example.TwoTierCache;

public class InMemoryDataStore implements IDataStore {
    @Override
    public String get(String key) {
        //In real implementation, a concrete value for this key will be fetched from a data store
        return "#" + key + "#";
    }
}
