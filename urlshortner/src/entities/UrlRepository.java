package urlshortner.src.entities;

public interface UrlRepository {
    public void save(UrlMapping urlMapping);
    public UrlMapping findByShortCode(String shortCode);
}
