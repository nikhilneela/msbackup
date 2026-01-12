package org.example.RateLimiter.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConstantRateRefillRule implements IRefillRule {
    private final int refillRate;

    @Override
    public void refillBucket(TokenBucket tokenBucket) {

    }
}
