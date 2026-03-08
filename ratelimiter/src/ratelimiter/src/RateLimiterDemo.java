package ratelimiter.src;

public class RateLimiterDemo {

    public static void main(String[] args) throws InterruptedException {
        RateLimiterService service = RateLimiterService.getInstance();
        int key = 1;

        // 5 requests per 2 seconds
        RateLimitConfig config = new RateLimitConfig(5, 2000);

        testLimiter("TOKEN_BUCKET", service.selectRateLimiter(RateLimitType.TOKEN_BUCKET, config), key, 10);

        System.out.println("\nSleeping 2100ms to enter next window...\n");
        Thread.sleep(2100);

        testLimiter("TOKEN_BUCKET (after wait)", service.selectRateLimiter(RateLimitType.TOKEN_BUCKET, config), key, 10);


        testLimiter("SLIDING_WINDOW_LOG", service.selectRateLimiter(RateLimitType.SLIDING_WINDOW_LOG, config), key, 10);

        System.out.println("\nSleeping 2100ms to enter next window...\n");
        Thread.sleep(2100);

        testLimiter("SLIDING_WINDOW_LOG (after wait)", service.selectRateLimiter(RateLimitType.SLIDING_WINDOW_LOG, config), key, 10);


        RateLimitConfig fixedCfg = new RateLimitConfig(3, 2000);
        testLimiter("FIXED_WINDOW", service.selectRateLimiter(RateLimitType.FIXED_WINDOW, fixedCfg), key, 10);

        System.out.println("\nSleeping 2100ms to enter next window...\n");
        Thread.sleep(2100);

        testLimiter("FIXED_WINDOW (after wait)", service.selectRateLimiter(RateLimitType.FIXED_WINDOW, fixedCfg), key, 10);
    }

    private static void testLimiter(String name, RateLimiter limiter, int key, int requests) {
        System.out.println("=== " + name + " ===");
        for (int i = 1; i <= requests; i++) {
            boolean allowed = limiter.allowRequest(key);
            System.out.println("Req#" + i + " => " + (allowed ? "Allowed" : "Blocked"));
        }
    }
}