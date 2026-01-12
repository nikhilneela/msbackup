package org.example.TwoTierCache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CacheItem {
    @Getter
    private final String value;
    private final long expiresAt;

    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }
}
