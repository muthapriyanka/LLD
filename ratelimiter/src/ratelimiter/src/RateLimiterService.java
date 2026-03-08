package ratelimiter.src;

public class RateLimiterService {
    static RateLimiterService instance;

    private RateLimiterService() {}

    public static RateLimiterService getInstance() {
        if (instance == null) {
            instance = new RateLimiterService();
        }
        return instance;
    }

    public RateLimiter selectRateLimiter(RateLimitType algo, RateLimitConfig config) {
        return RateLimiterFactory.createRateLimiter(algo, config);
    }
}
