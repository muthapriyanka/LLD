import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {

        ParkingLot parkingLot = ParkingLot.getInstance();

        // 1. Initialize the parking lot with floors and spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot(1, VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot(2, VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot(3, VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot(1, VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot(2, VehicleSize.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        parkingLot.setFeeStrategy(new VehicleBasedFee());


        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Entries ---");


        Vehicle bike = new Bike("B-123", VehicleSize.SMALL);
        Vehicle car = new Car("C-456", VehicleSize.MEDIUM);

        Ticket bikeTicket = parkingLot.park(bike, 0); // Park bike on floor 1
            if (bikeTicket != null) {
                System.out.println("Bike parked with ticket ID: " + bikeTicket.getTicketId());
            } else {
                System.out.println("No parking spot available for bike.");
            }
       
            if (bikeTicket != null) {
                Optional<Double> feeOpt = parkingLot.unpark(bikeTicket);
                double fee = feeOpt.orElse(0.0);
                System.out.printf("Bike B-123 unparked. Fee: $%.2f%n", fee);        }       
    }
}
