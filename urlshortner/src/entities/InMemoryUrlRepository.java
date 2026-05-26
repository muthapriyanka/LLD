package urlshortner.src.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlRepository implements UrlRepository {
    private final Map<String, UrlMapping> shortCodeToMapping = new ConcurrentHashMap<>();
    private final Map<String, UrlMapping> longUrlToMapping = new ConcurrentHashMap<>();

    @Override
    public void save(UrlMapping mapping) {
        shortCodeToMapping.put(mapping.getShortCode(), mapping);
        longUrlToMapping.put(mapping.getLongUrl(), mapping);
    }

    @Override
    public UrlMapping findByShortCode(String shortCode) {
        return shortCodeToMapping.get(shortCode);
    }

    @Override
    public UrlMapping findByLongUrl(String longUrl) {
        return longUrlToMapping.get(longUrl);
    }
}
