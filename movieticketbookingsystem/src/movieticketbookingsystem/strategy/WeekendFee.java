package movieticketbookingsystem.strategy;

import movieticketbookingsystem.entities.SeatType;
import movieticketbookingsystem.entities.Show;

public class WeekendFee implements FeeStrategy {

    @Override
    public double calculateFee(Show show, SeatType seatType) {
        return seatType.getPrice() * 1.2;
    }
    
}
