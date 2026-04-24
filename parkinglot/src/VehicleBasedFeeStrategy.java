import java.util.Map;

public class VehicleBasedFeeStrategy implements feestrategy {
    private static final Map<VehicleSize, Double> HOURLY_RATES = Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.MEDIUM, 20.0,
            VehicleSize.LARGE, 30.0
    );

    @Override
    public int calculatefees (Ticket parkingTicket) {
        long duration = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return (int) (hours * HOURLY_RATES.get(parkingTicket.getVehicle().getSize()));
    }
}