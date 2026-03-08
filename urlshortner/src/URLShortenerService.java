package urlshortner.src;

import urlshortner.src.entities.Encoder;
import urlshortner.src.entities.IdGenerator;
import urlshortner.src.entities.UrlMapping;
import urlshortner.src.entities.UrlRepository;

public class URLShortenerService {
    private final IdGenerator idGenerator;
    private final Encoder encoder;
    private final UrlRepository urlRepository;

    public URLShortenerService(IdGenerator idGenerator, Encoder encoder, UrlRepository urlRepository) {
        this.idGenerator = idGenerator;
        this.encoder = encoder;
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        long id = idGenerator.nextId();
        String shortCode = encoder.encode(id);
        UrlMapping mapping = new UrlMapping(shortCode, originalUrl);
        urlRepository.save(mapping);
        return shortCode;
    }

    public String longurl(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode);
        return mapping != null ? mapping.getLongUrl() : null;
    }
}
