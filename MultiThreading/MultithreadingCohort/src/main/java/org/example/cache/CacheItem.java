package org.example.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CacheItem {
    private String value;
    private long expiresAtMillis;

    public boolean isExpired() {
        return expiresAtMillis < System.currentTimeMillis();
    }
}
