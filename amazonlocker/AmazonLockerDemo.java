package amazonlocker;

public class AmazonLockerDemo {
    public static void main(String[] args) {
        Compartment[] compartments = {
                new Compartment("C1", Size.SMALL),
                new Compartment("C2", Size.MEDIUM),
                new Compartment("C3", Size.LARGE)
        };

        Locker locker = new Locker("LOCKER-1", compartments);
        LockerService lockerService = new LockerService(locker);

        // Delivery
        PackageItem pkg1 = new PackageItem("PKG-101", Size.SMALL);
        String token1 = lockerService.depositPackage(pkg1);

        locker.printState();

        // Pickup
        System.out.println("\nPickup using token: " + token1);
        lockerService.pickupPackage(token1);

        locker.printState();
    }
}