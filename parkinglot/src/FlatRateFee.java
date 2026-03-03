public class FlatRateFee implements feestrategy {

    @Override
    public int calculatefees(Ticket ticket) {
        return 20;
    }
    
}
