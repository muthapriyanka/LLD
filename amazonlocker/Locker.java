package amazonlocker;

public class Locker {
    private final String lockerId;
    private final Compartment[] compartments;

    public Locker(String lockerId, Compartment[] compartments) {
        this.lockerId = lockerId;
        this.compartments = compartments;
    }

    public Compartment[] getCompartments() {
        return compartments;
    }

     public void printState() {
        System.out.println("\nLocker State: " + lockerId);
        for (Compartment c : compartments) {
            System.out.println(c);
        }
    }
}
