import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<Integer> seatIds;
    private final double amount;
    private BookingStatus bookingStatus;

    public Booking(User user, Show show, List<Integer> seatIds, double amount) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seatIds = seatIds;
        this.amount = amount;
        this.bookingStatus = BookingStatus.PENDING;
    }

    // getters...
    public String getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Show getShow() { return show; }
    public List<Integer> getSeatIds() { return seatIds; }
    public double getAmount() { return amount; }
    public BookingStatus getBookingStatus() { return bookingStatus; }
    public void setStatus(BookingStatus status) { this.bookingStatus = status; }
}