

package fooddeliveryservice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    String orderId;
    User user;
    DeliveryAgent deliveryAgent;
    Restaurant restaurant;
    List<OrderLineItem> orderLineItems;
    OrderStatus orderStatus;
    int totalAmount;

    public Order(User user, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        this.orderId = UUID.randomUUID().toString(); // Generate a unique order ID
        this.user = user;
        this.restaurant = restaurant;
        this.orderLineItems = new ArrayList<>(orderLineItems);
        this.totalAmount = calculateTotalAmount();
    }

    private int calculateTotalAmount() {
        return orderLineItems.stream()
                .mapToInt(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return new ArrayList<>(orderLineItems);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void assignDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }
    
}
