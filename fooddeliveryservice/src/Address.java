

public class Address {
    String city;
    String state;
    int pincode;
    String street;
    private double latitude;
    private double longitude;

    public Address(String city, String state, int pincode, String street, double latitude, double longitude) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
    }   

    public String getCity() {
        return city;
    }       
    public String getState() {
        return state;
    }
    public int getPincode() {
        return pincode;
    }
    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;                 
    }
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double distanceTo(Address other) {
        double latDiff = this.latitude - other.latitude;
        double lonDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);  // for simplicity, using Euclidean distance. In real-world, consider using Haversine formula for geographic distance.
    }
}
