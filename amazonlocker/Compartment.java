package amazonlocker;

public class Compartment {
    private final String compartmentId;
    private final Size size;
    private boolean occupied;
    private PackageItem currentPackage;

    public Compartment(String compartmentId, Size size) {
        this.compartmentId = compartmentId;
        this.size = size;
        this.occupied = false;
    }

    public String getCompartmentId() {
        return compartmentId;
    }

    public Size getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void assignPackage(PackageItem packageItem) {
        this.currentPackage = packageItem;
        this.occupied = true;
    }

    public void removePackage() {
        this.currentPackage = null;
        this.occupied = false;
    }

    public PackageItem getCurrentPackage() {
        return currentPackage;
    }

    public void open() {
        System.out.println("Opening compartment: " + compartmentId);
    }

    @Override
    public String toString() {
        String pkg = currentPackage == null ? "empty" : currentPackage.getPackageId();
        return "Compartment{id='" + compartmentId + "', size=" + size +
                ", occupied=" + occupied + ", package=" + pkg + "}";
    }
}
