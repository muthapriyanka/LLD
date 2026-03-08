package ratelimiter.src;

public class User {
    String id;
    String name;

    public User(String name) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
