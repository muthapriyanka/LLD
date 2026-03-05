

import java.util.List;
import java.util.UUID;

public class Order {
    int oderid;
    User user;
    DeliveryAgent deliveryAgent;
    Restaurant restaurant;
    List<OrderLineItem> orderLineItems;
    OrderStatus orderStatus;
    List<Observer> observers;

    public Order(User user, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        this.oderid = UUID.randomUUID().hashCode(); // Generate a unique order ID
        this.user = user;
        this.restaurant = restaurant;
        this.orderLineItems = orderLineItems;
    }
    public int getOderid() {
        return oderid;
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
        return orderLineItems;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void assignDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }
    
}
