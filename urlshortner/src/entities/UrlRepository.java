package urlshortner.src.entities;

public interface UrlRepository {
    void save(UrlMapping urlMapping);

    UrlMapping findByShortCode(String shortCode);

    UrlMapping findByLongUrl(String longUrl);
}
