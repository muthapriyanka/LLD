import java.util.List;

public class BookingManager {
    private static BookingManager instance;

    private BookingManager() {}

    public static synchronized BookingManager getInstance() {
        if (instance == null) instance = new BookingManager();
        return instance;
    }

    public Booking createBooking(User user, Show show, List<Integer> seatIds,
                                 Payment payment, FeeStrategy feeStrategy) {

        // 1) Validate seats exist and are free, compute total
        double totalFee = 0.0;
        for (Integer seatId : seatIds) {
            Seat seat = show.getSeat(seatId);
            if (seat == null) {
                System.out.println("Seat " + seatId + " not found for this show.");
                return null;
            }
            if (seat.isBooked()) {
                System.out.println("Seat " + seatId + " already booked for this show.");
                return null;
            }
            totalFee += feeStrategy.calculateFee(show, seat.getSeatType());
        }

        // 2) Charge once
        boolean paymentSuccess = payment.processPayment(totalFee);
        if (!paymentSuccess) {
            System.out.println("Payment failed. Booking not created.");
            return null;
        }

        // 3) Try to book seats (atomic per seat)
        for (Integer seatId : seatIds) {
            Seat seat = show.getSeat(seatId);
            boolean booked = seat.book();
            if (!booked) {
                // Ideally refund payment here. At minimum log and fail cleanly.
                System.out.println("Seat " + seatId + " was booked by somebody else. Booking failed.");
                // Partial rollback: unbook any seats we already booked in this loop
                for (Integer sId : seatIds) {
                    Seat s = show.getSeat(sId);
                    if (s != null && s.isBooked()) {
                        // naive rollback — in a real system you'd track which you booked
                        s.unbook();
                    }
                }
                return null;
            }
        }

        // 4) Create booking record
        Booking booking = new Booking(user, show, seatIds, totalFee);
        booking.setStatus(BookingStatus.CONFIRMED);
        user.addBooking(booking);
        System.out.println("Booking confirmed: " + booking.getBookingId());
        return booking;
    }

    public void cancelBooking(Booking booking) {
        booking.setStatus(BookingStatus.CANCELLED);
        // unbook seats for show (iterate and unbook seat numbers)
    }
}