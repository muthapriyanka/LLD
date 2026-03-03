import java.util.*;

public class ParkingLot {
    static ParkingLot instance;
    Vehicle vehicle;
    List<ParkingFloor> floors;
    private feestrategy feeStrategy;

    private ParkingLot() {
        this.floors = new ArrayList<>();
    }
    public static synchronized ParkingLot getInstance() {
        if(instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }       

    public Ticket park(Vehicle vehicle, int floorId) {
        ParkingFloor parkingFloor = floors.get(floorId);
        List<ParkingSpot> availableSpots = parkingFloor.getAvailableParkingSpots();
        for(ParkingSpot parkingSpot : availableSpots) {
            if(parkingSpot.getsize() == vehicle.getSize()) {
                parkingSpot.park(vehicle);
                Ticket ticket = new Ticket(vehicle, parkingSpot);
                return ticket;
            }
        }
        return null;

    }
    public void setFeeStrategy (feestrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public Optional<Double> unpark(Ticket ticket) {
        ParkingSpot parkingSpot = ticket.getSpot();
        parkingSpot.unpark();
        ticket.setExitTimestamp();
        if (feeStrategy != null) {
            int fee = feeStrategy.calculatefees(ticket);
            return Optional.of((double) fee);
        }
        return Optional.empty();
    }
}
