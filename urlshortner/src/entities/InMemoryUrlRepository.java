package urlshortner.src.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlRepository implements UrlRepository {
    private final Map<String, UrlMapping> storage = new ConcurrentHashMap<>();

    @Override
    public void save(UrlMapping mapping) {
        storage.put(mapping.getShortCode(), mapping);
    }

    @Override
    public UrlMapping findByShortCode(String shortCode) {
        return storage.get(shortCode);
    }
}
