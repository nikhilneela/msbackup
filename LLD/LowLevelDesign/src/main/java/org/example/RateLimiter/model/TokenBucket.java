package org.example.RateLimiter.model;

import lombok.Getter;
import lombok.Setter;

public class TokenBucket {
    private final String id;
    @Getter
    @Setter
    private long lastRefillTimeInMills;
    @Getter
    private final int maxSize;
    @Getter
    private final int tokensPerSecond;
    @Setter
    @Getter
    private int currentTokens;

    public TokenBucket(final String id, final int maxSize, final int tokensPerSecond) {
        this.id = id;
        this.maxSize = maxSize;
        this.tokensPerSecond = tokensPerSecond;
        this.lastRefillTimeInMills = System.currentTimeMillis();
        this.currentTokens = maxSize;
    }

    private boolean reduceTokens() {
        if (this.currentTokens == 0) {
            return false;
        }
        this.currentTokens--;
        return true;
    }

    public synchronized boolean tryConsume() {
        refill();
        return reduceTokens();
    }

    private void refill() {
        long currentTimeInMills = System.currentTimeMillis();
        int diff = (int)(currentTimeInMills - lastRefillTimeInMills);
        int tokensToAdd = (diff * tokensPerSecond / 1000);

        if (tokensToAdd > 0) {
            this.currentTokens = Math.min(this.maxSize, this.currentTokens + tokensToAdd);
            this.lastRefillTimeInMills = currentTimeInMills;
        }
    }
}
