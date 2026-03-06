

package onlineshoppingapp;

public class Address {
    String street;
    String city;
    String state;
    String zipcode;
    

    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }   

    public String getCity() {
        return city;
    }       
    public String getState() {
        return state;
    }
    public String getZipcode() {
        return zipcode;
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
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setStreet(String street) {
        this.street = street;
    }
   
}
