package movieticketbookingsystem.strategy;

import movieticketbookingsystem.entities.SeatType;
import movieticketbookingsystem.entities.Show;

public interface FeeStrategy {
    double calculateFee(Show show, SeatType seatType);
}
