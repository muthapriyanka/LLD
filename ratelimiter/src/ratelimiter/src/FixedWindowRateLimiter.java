package ratelimiter.src;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter extends RateLimiter {
    private final Map<Integer, Integer> requestCount = new ConcurrentHashMap<>(); //key:userid, value: no of request made
    private final Map<Integer, Long> windowStart = new ConcurrentHashMap <>();    // key:userid, value: timestamp

    public FixedWindowRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(int id) {
        int currenttime = (int) (System.currentTimeMillis() / 1000); // current time in seconds
        long windowStartTime = windowStart.getOrDefault(id, 0L);
        int count = requestCount.getOrDefault(id, 0);
        if (currenttime - windowStartTime >= config.getTimewindow()) {
            // Reset the window
            windowStart.put(id, (long)currenttime);
            requestCount.put(id, 1);
            return true;
        } else {
            if (count < config.getMaxrequests()) {
                requestCount.put(id, count + 1);
                return true;
            } 
        }
        return false; // Placeholder return value
    }

}
