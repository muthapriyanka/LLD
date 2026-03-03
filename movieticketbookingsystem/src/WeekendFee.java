public class WeekendFee implements FeeStrategy {

    @Override
    public double calculateFee(Show show, SeatType seatType) {
        int baseFee = switch (seatType) {
            case REGULAR -> 15;
            case PREMIUM -> 25;
            case RECLINER -> 40;
        };
        // Assuming weekend fee is 20% more than weekday fee
        return (double) (baseFee * 1.2);
    }
    
}
