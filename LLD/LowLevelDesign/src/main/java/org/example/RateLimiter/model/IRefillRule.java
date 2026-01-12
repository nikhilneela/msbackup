package org.example.RateLimiter.model;

public interface IRefillRule {
    void refillBucket(TokenBucket tokenBucket);
}
