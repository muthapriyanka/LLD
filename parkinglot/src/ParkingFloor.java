import java.util.*;

public class ParkingFloor {
    int floorId;
    final Map<Integer, ParkingSpot> spots;
    Set<ParkingSpot> availableSpots;

    public ParkingFloor(int floorId) {
        this.floorId = floorId;
        this.spots = new HashMap<>();
        this.availableSpots = new HashSet<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        this.spots.put(parkingSpot.getid(), parkingSpot);
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots.values()) {
            if (spot.canFitVehicle(vehicle)) {
                return spot;
            }
        }
        return null; // no spot found
    }
}