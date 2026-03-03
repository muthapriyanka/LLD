import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;
    String name;
    List<Booking> bookings;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public int getUserId() {
        return userId;
    }

}
