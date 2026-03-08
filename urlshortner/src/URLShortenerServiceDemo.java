package urlshortner.src;

import urlshortner.src.entities.AtomicIdGenerator;
import urlshortner.src.entities.Base62Encoder;
import urlshortner.src.entities.Encoder;
import urlshortner.src.entities.IdGenerator;
import urlshortner.src.entities.InMemoryUrlRepository;
import urlshortner.src.entities.UrlRepository;

public class URLShortenerServiceDemo {
    public static void main(String[] args) {
        String originalUrl = "https://www.example.com/some/very/long/url";
        IdGenerator idGenerator = new AtomicIdGenerator();
        Encoder encoder = new Base62Encoder();
        UrlRepository urlRepository = new InMemoryUrlRepository();
        URLShortenerService urlShortenerService = new URLShortenerService(idGenerator, encoder, urlRepository);
        String shortUrl = urlShortenerService.shortenUrl(originalUrl);
        System.out.println("Short URL: http://short.ly/" + shortUrl);
        String retrievedUrl = urlShortenerService.longurl(shortUrl);
        System.out.println("Retrieved URL: " + retrievedUrl);
    }
    
}
