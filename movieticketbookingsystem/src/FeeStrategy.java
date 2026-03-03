public interface FeeStrategy {
    double calculateFee(Show show, SeatType seatType);
}