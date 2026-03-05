

import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryService {
    static FoodDeliveryService instance;
    List<User> users;
    List<Restaurant> restaurants;
    List<DeliveryAgent> deliveryAgents;
    List<Order> orders;
    List<Observer> observers;
    DeliveryAgentStrategy deliveryAgentStrategy;
    
    private FoodDeliveryService() {
        users = new ArrayList<>();
        restaurants = new ArrayList<>();
        deliveryAgents = new ArrayList<>();
        orders = new ArrayList<>();
        observers = new ArrayList<>();
        deliveryAgentStrategy = new NearestAvailableAgentStrategy(); // Default strategy
    }

    public static FoodDeliveryService getInstance() {
        if (instance == null) {
            instance = new FoodDeliveryService();
        }
        return instance;
    }       
    
    public void addUser(User user) {
        users.add(user);
    }
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }
    public void addDeliveryAgent(DeliveryAgent deliveryAgent) {
        deliveryAgents.add(deliveryAgent);
    }
    public void addObserver(Observer observer) {    
        observers.add(observer);
    }

 
    public synchronized Order placeOrder(User user, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        Order order = new Order(user, restaurant, orderLineItems);
        orders.add(order);
        order.setOrderStatus(OrderStatus.PLACED);
        // Notify observers after order is confirmed as placed
        System.out.println("order placed with ID: " + order.getOderid());

        observers.forEach(observer -> observer.update(order));
        return order;
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        order.setOrderStatus(newStatus);
        observers.forEach(observer -> observer.update(order));
    }

    public void cancelOrder(Order order) {
        order.setOrderStatus(OrderStatus.CANCELLED);
        observers.forEach(observer -> observer.update(order));
    }
    
    public void assignDeliveryAgent(Order order) {
        deliveryAgentStrategy.findAgent(order, deliveryAgents).ifPresent(agent -> {
            order.assignDeliveryAgent(agent);
            // Add the assigned agent to observers if not already added
            if (!observers.contains(agent)) {
                observers.add(agent);
            }
            // Only notify the assigned delivery agent about the assignment
            agent.update(order);
            order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        });
    }
    
}
