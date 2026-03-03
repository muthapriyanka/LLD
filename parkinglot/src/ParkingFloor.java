import java.util.*;

public class ParkingFloor {
    int floorId;
    final Map<Integer, ParkingSpot> spots;
    List<ParkingSpot> availableSpots;

    public ParkingFloor(int floorId) {
        this.floorId = floorId;
        this.spots = new HashMap<>();
        this.availableSpots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        this.spots.put(parkingSpot.getid(), parkingSpot);
    }

    public List<ParkingSpot> getAvailableParkingSpots() {
        for(ParkingSpot parkingSpot : this.spots.values()) {
            if(parkingSpot.isavailable()) {
                availableSpots.add(parkingSpot);
            }
        }
        return availableSpots;
    }   
}