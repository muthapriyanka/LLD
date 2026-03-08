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
}


// We need the UrlMapping class to represent the data model (entity) for a URL record.
// It encapsulates all information related to a shortened URL in one object.

// 1. What UrlMapping Represents

// Each shortened URL is essentially a record like:

// shortCode	longUrl	createdAt
// abc123	https://google.com/maps
// 	171000000

// Instead of storing raw values separately, we store them as an object:

// UrlMapping

// So one object = one mapping between short URL and long URL.

// 2. Without UrlMapping (Bad Design)

// If we did this:

// Map<String, String> map = new HashMap<>();

// map.put(shortCode, longUrl);

// Problems:

// ❌ Cannot store metadata
// ❌ Hard to extend
// ❌ No encapsulation
// ❌ Violates clean design

// 3. With UrlMapping (Good Design)
// public class UrlMapping {

//     private final String shortCode;
//     private final String longUrl;
//     private final long createdAt;

// }

// Now repository stores:

// Map<String, UrlMapping>

// Benefits:

// ✅ Encapsulation
// ✅ Easy to extend
// ✅ Cleaner code
// ✅ Follows OO principles

// 4. Extensibility (Main Reason)

// Later we may need to add features like:

// class UrlMapping {

//     String shortCode;
//     String longUrl;

//     long createdAt;
//     long expiryTime;

//     int clickCount;

//     String createdByUser;

// }

// If we used only:

// Map<String,String>

// we would need to rewrite the whole system.