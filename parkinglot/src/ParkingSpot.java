public class ParkingSpot {
    int spotId;
    VehicleSize size;
    boolean isavailable;
    Vehicle vehicle;

    public ParkingSpot(int spotId, VehicleSize size) {
        this.spotId = spotId;
        this.size = size;
        this.isavailable = true;    
    }

    public int getid() {
        return this.spotId;
    }

    public VehicleSize getsize() {
        return this.size;
    }

    public boolean isavailable() {
        return this.isavailable;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isavailable = false;
    }       

    public void unpark() {
        this.isavailable = true;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if (isavailable) return false;

        switch (vehicle.getSize()) {
            case SMALL:
                return size == VehicleSize.SMALL;
            case MEDIUM:
                return size == VehicleSize.MEDIUM || size == VehicleSize.LARGE;
            case LARGE:
                return size == VehicleSize.LARGE;
            default:
                return false;
        }
    }
}
