package org.example.RateLimiter.strategy;

import lombok.AllArgsConstructor;
import org.example.RateLimiter.model.IRateLimitEntity;
import org.example.RateLimiter.model.TokenBucket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class TokenBucketRateLimitStrategy implements IRateLimitStrategy {
    private final Map<String, TokenBucket> tokenBucketMap;

    public TokenBucketRateLimitStrategy() {
        this.tokenBucketMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(IRateLimitEntity entity) {
        TokenBucket bucket = tokenBucketMap.computeIfAbsent(entity.getId(), id -> new TokenBucket(id, 500, 10));
        return bucket.tryConsume();
    }
}
