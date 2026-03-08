package ratelimiter.src;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter extends RateLimiter {
    private final Map<Integer, Integer> tokens = new ConcurrentHashMap<>(); // key:userid, value: no of tokens currently present in bucket
    private final Map<Integer, Long> lastRefillTime = new ConcurrentHashMap <>(); // key:userid, value: timestamp

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(int id) {
        long currentTime = System.currentTimeMillis() / 1000; // current time in seconds
        long lastRefill = lastRefillTime.getOrDefault(id, currentTime);
        int currentTokens = tokens.getOrDefault(id, config.getMaxrequests());

        // Refill tokens based on time elapsed
        long timeElapsed = currentTime - lastRefill;
        double refillRate = (double) config.getMaxrequests() / config.getTimewindow(); // tokens per second
        int tokensToAdd = (int) (timeElapsed * refillRate);
        if (tokensToAdd > 0) {
            currentTokens = Math.min(config.getMaxrequests(), currentTokens + tokensToAdd);
            lastRefillTime.put(id, currentTime);
        }

        if (currentTokens > 0) {
            currentTokens--;
            tokens.put(id, currentTokens);
            return true;
        }
        return false;
    }
}