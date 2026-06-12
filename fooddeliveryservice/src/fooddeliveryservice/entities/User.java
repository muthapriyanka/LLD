

package fooddeliveryservice.entities;

import java.util.ArrayList;
import java.util.List;

import fooddeliveryservice.observer.Observer;

public class User implements Observer {
    int userId;
    String name;
    Address address;
    List<Order> orders;

    public User(int userId, String name, Address address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.orders = new ArrayList<>();
    }           

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
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
        System.out.println("User " + name + " notified about order " + order.getOrderId() + " status: " + order.getOrderStatus());
    }   

}
