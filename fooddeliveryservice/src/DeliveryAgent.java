

import java.util.concurrent.atomic.AtomicBoolean;

public class DeliveryAgent implements Observer {
    int agentId;
    String name;
    String phoneNumber;
    Address currentLocation;
    AtomicBoolean isAvailable;

    public DeliveryAgent(Address address, String name, int agentId, String phoneNumber) {
        this.agentId = agentId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.currentLocation = address;
        this.isAvailable = new AtomicBoolean(true); 
    }

    public int getAgentId() {
        return agentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable.get();
    }

    public void setAvailable(boolean available) {
        isAvailable.set(available);
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(Order order) {
        // Notify the delivery agent about the order status update
        System.out.println("Delivery Agent " + name + " notified about order " + order.getOderid() + " status update.");
    }   

}
