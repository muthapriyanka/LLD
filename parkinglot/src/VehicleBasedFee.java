public class VehicleBasedFee implements feestrategy {
    @Override
    public int calculatefees(Ticket ticket) {
        // simple example; adjust as you want
        Vehicle v = ticket.getVehicle();
        return switch (v.getSize()) {
            case SMALL -> 10;
            case MEDIUM -> 20;
            case LARGE -> 30;
        };
    }
}