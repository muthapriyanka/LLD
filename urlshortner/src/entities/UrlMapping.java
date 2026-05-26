package urlshortner.src.entities;

public class UrlMapping {

    private final String shortCode;
    private final String longUrl;
    private final long createdAt;

    public UrlMapping(String shortCode, String longUrl) {
        this.shortCode = shortCode;
        this.longUrl = longUrl;
        this.createdAt = System.currentTimeMillis();
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
