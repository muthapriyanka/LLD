public abstract class Vehicle {

    String number;
    VehicleSize size;

    public Vehicle(String number, VehicleSize size) {
        this.number = number;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }
    public String getNumber() {
        return number;
    }
}
