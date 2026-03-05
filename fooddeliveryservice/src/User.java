

public class User implements Observer {
    int userId;
    String name;
    Address address;

    public User(int userId, String name, Address address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }           
    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public void update(Order order) {
        // Notify the user about the order status update
        System.out.println("User " + name + " notified about order " + order.getOderid() + " status update.");
    }   

}
