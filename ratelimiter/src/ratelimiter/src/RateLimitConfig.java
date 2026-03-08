package ratelimiter.src;

public class RateLimitConfig {
    int maxrequests;
    int timewindow; // in seconds
    public RateLimitConfig(int maxrequests, int timewindow) {
        this.maxrequests = maxrequests;
        this.timewindow = timewindow;
    }

    public int getMaxrequests() {
        return maxrequests;
    }

    public int getTimewindow() {
        return timewindow;
    }


}
