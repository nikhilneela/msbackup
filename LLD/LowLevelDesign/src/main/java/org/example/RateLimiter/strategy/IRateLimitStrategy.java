package org.example.RateLimiter.strategy;

import org.example.RateLimiter.model.IRateLimitEntity;

public interface IRateLimitStrategy {
    boolean isRequestAllowed(IRateLimitEntity entity);
}
