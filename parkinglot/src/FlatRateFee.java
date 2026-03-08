public class FlatRateFee implements feestrategy {
    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public int calculatefees(Ticket ticket) {
        long duration = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return (int) (hours * RATE_PER_HOUR);
    }
    
}
