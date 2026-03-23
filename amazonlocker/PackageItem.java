package amazonlocker;

public class PackageItem {
    private final String packageId;
    private final Size size;

    public PackageItem(String packageId, Size size) {
        this.packageId = packageId;
        this.size = size;
    }

    public String getPackageId() {
        return packageId;
    }

    public Size getSize() {
        return size;
    }
}
