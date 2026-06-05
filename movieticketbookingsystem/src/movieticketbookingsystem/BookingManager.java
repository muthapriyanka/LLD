package movieticketbookingsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import movieticketbookingsystem.entities.Booking;
import movieticketbookingsystem.entities.BookingStatus;
import movieticketbookingsystem.entities.Seat;
import movieticketbookingsystem.entities.Show;
import movieticketbookingsystem.entities.User;
import movieticketbookingsystem.strategy.FeeStrategy;
import movieticketbookingsystem.strategy.Payment;

public class BookingManager {
    private static BookingManager instance;

    private BookingManager() {}

    public static synchronized BookingManager getInstance() {
        if (instance == null) instance = new BookingManager();
        return instance;
    }

    public Booking createBooking(User user, Show show, List<Integer> seatIds,
                                 Payment payment, FeeStrategy feeStrategy) {
        if (user == null || show == null || payment == null || feeStrategy == null) {
            System.out.println("Invalid booking request.");
            return null;
        }

        if (seatIds == null || seatIds.isEmpty()) {
            System.out.println("At least one seat must be selected.");
            return null;
        }

        Set<Integer> uniqueSeatIds = new HashSet<>();
        for (Integer seatId : seatIds) {
            if (seatId == null || !uniqueSeatIds.add(seatId)) {
                System.out.println("Duplicate or invalid seat selected: " + seatId);
                return null;
            }
        }

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

        // 2) Reserve/book seats before charging payment.
        List<Seat> bookedSeats = new ArrayList<>();
        for (Integer seatId : seatIds) {
            Seat seat = show.getSeat(seatId);
            boolean booked = seat.book();
            if (!booked) {
                System.out.println("Seat " + seatId + " was booked by somebody else. Booking failed.");
                for (Seat bookedSeat : bookedSeats) {
                    bookedSeat.unbook();
                }
                return null;
            }
            bookedSeats.add(seat);
        }

        // 3) Create booking record in pending state before payment.
        Booking booking = new Booking(user, show, seatIds, totalFee);
        user.addBooking(booking);

        // 4) Charge once after seats are reserved.
        boolean paymentSuccess = payment.processPayment(totalFee);
        if (!paymentSuccess) {
            for (Seat bookedSeat : bookedSeats) {
                bookedSeat.unbook();
            }
            booking.setStatus(BookingStatus.CANCELLED);
            System.out.println("Payment failed. Booking not created.");
            return null;
        }

        // 5) Confirm booking after payment succeeds.
        booking.setStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking confirmed: " + booking.getBookingId());
        return booking;
    }

    public void cancelBooking(Booking booking) {
        if (booking == null) {
            System.out.println("Invalid booking.");
            return;
        }

        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        for (Integer seatId : booking.getSeatIds()) {
            Seat seat = booking.getShow().getSeat(seatId);
            if (seat != null) {
                seat.unbook();
            }
        }

        booking.setStatus(BookingStatus.CANCELLED);
        System.out.println("Booking cancelled: " + booking.getBookingId());
    }
}
