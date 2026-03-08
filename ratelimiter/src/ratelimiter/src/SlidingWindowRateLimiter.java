package ratelimiter.src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowRateLimiter extends RateLimiter {
    private final HashMap<Integer, Queue<Long>> requestTimestamps = new HashMap<>(); // key: userId, value: queue of request timestamps
    public SlidingWindowRateLimiter(RateLimitConfig config) {
        super(config);
    }

    @Override
    public boolean allowRequest(int id) {
            long currentTime = System.currentTimeMillis() / 1000; // current time in seconds
            Queue<Long> timestamps = requestTimestamps.getOrDefault(id, new LinkedList<>());

            // Remove timestamps that are outside the time window
            while (!timestamps.isEmpty() && timestamps.peek() <= currentTime - config.getTimewindow()) { //timestamps.peek() returns the first element (head of the queue), which is the oldest timestamp in this case.
                timestamps.poll();
            }

            if (timestamps.size() < config.getMaxrequests()) {
                timestamps.offer(currentTime);
                requestTimestamps.put(id, timestamps);
                return true;
            }

        return false; // Placeholder return value
    }
    
}
