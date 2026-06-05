package movieticketbookingsystem.strategy;

import movieticketbookingsystem.entities.SeatType;
import movieticketbookingsystem.entities.Show;

public class WeekdayFee implements FeeStrategy {
    @Override
    public double calculateFee(Show show, SeatType seatType) {
        double base = seatType.getPrice(); // or custom logic
        // discount or add based on show/time
        return base;
    }
}
