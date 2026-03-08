package ratelimiter.src;

public abstract class RateLimiter {
    protected RateLimitConfig config;

    public RateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    public abstract boolean allowRequest(int id);
    
}
