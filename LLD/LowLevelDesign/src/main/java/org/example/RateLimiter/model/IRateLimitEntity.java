package org.example.RateLimiter.model;

import java.util.Optional;

public interface IRateLimitEntity {
    String getId();
    IRefillRule getRefillRule();
}
