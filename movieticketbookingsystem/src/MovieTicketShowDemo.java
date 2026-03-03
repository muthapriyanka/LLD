import java.util.Arrays;

public class MovieTicketShowDemo {
    public static void main(String[] args) {
        Movie movie1 = new Movie("Inception", "Sci-Fi", "English", 148);
        Screen screen1 = new Screen(1);

        // Create seats for the screen (template/layout)
        screen1.addSeat(new Seat(1, 1, SeatType.REGULAR));
        screen1.addSeat(new Seat(1, 2, SeatType.PREMIUM));
        screen1.addSeat(new Seat(1, 3, SeatType.RECLINER));

        MovieBookingSystem bookingSystem = MovieBookingSystem.getInstance();

        // IMPORTANT: show should create its own per-show seat list (copy from screen)
        Show show1 = bookingSystem.addShow(1, 1800, movie1, screen1);

        User user1 = bookingSystem.createUser(1, "Alice");

        FeeStrategy feeStrategy = new WeekdayFee();
        BookingManager bookingManager = BookingManager.getInstance();
        Payment payment = new UPIPayment();

        Booking booking1 = bookingManager.createBooking(
                user1,
                show1,
                Arrays.asList(1), // seat number(s)
                payment,
                feeStrategy
        );

        if (booking1 != null) {
            System.out.println("Booking Status: " + booking1.getBookingStatus());
            System.out.println("Booking Id: " + booking1.getBookingId());
            System.out.println("Seats: " + booking1.getSeatIds());
            System.out.println("Amount: " + booking1.getAmount());
        } else {
            System.out.println("Booking failed");
        }
    }
}