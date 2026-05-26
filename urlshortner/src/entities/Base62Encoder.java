package urlshortner.src.entities;

public class Base62Encoder implements Encoder {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String encode(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }

        if (id == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            int remainder = (int) (id % 62);
            sb.append(BASE62.charAt(remainder));
            id /= 62;
        }
        return sb.reverse().toString();
    }
    
}
