package fooddeliveryservice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fooddeliveryservice.observer.Observer;

public class Restaurant implements Observer {
    int restaurantId;
    String name;
    Address address;
    Menu menu;
    List<Order> orders;

    public Restaurant(String name, Address address, Menu menu) {
        restaurantId = UUID.randomUUID().hashCode(); // Generate a unique restaurant ID
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Menu getMenu() {
        return menu;
    }

    public void addToMenu(MenuItem item) { 
        this.menu.addItem(item); 
    }

    @Override
    public void update(Order order) {
        // Notify the restaurant about the order status update
        System.out.println("Restaurant " + name + " notified about order " + order.getOrderId() + " status: " + order.getOrderStatus());
    }

    
}
