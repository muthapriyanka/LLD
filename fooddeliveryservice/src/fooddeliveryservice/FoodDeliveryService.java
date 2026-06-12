

package fooddeliveryservice;

import java.util.ArrayList;
import java.util.List;

import fooddeliveryservice.entities.DeliveryAgent;
import fooddeliveryservice.entities.Order;
import fooddeliveryservice.entities.OrderLineItem;
import fooddeliveryservice.entities.OrderStatus;
import fooddeliveryservice.entities.Restaurant;
import fooddeliveryservice.entities.User;
import fooddeliveryservice.strategy.DeliveryAgentStrategy;
import fooddeliveryservice.strategy.NearestAvailableAgentStrategy;

public class FoodDeliveryService {
    static FoodDeliveryService instance;
    List<User> users;
    List<Restaurant> restaurants;
    List<DeliveryAgent> deliveryAgents;
    List<Order> orders;
    DeliveryAgentStrategy deliveryAgentStrategy;
    
    private FoodDeliveryService() {
        users = new ArrayList<>();
        restaurants = new ArrayList<>();
        deliveryAgents = new ArrayList<>();
        orders = new ArrayList<>();
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

 
    public synchronized Order placeOrder(User user, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        if (!isValidOrder(user, restaurant, orderLineItems)) {
            return null;
        }

        Order order = new Order(user, restaurant, orderLineItems);
        orders.add(order);
        user.addOrder(order);
        restaurant.addOrder(order);
        order.setOrderStatus(OrderStatus.PLACED);
        // Notify only the user and restaurant involved in this order.
        System.out.println("order placed with ID: " + order.getOrderId() + ", amount: " + order.getTotalAmount());

        notifyOrderParticipants(order);
        return order;
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        if (order == null || newStatus == null) {
            return;
        }

        order.setOrderStatus(newStatus);
        if (newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELLED) {
            releaseDeliveryAgent(order);
        }
        notifyOrderParticipants(order);
    }

    public void cancelOrder(Order order) {
        if (order == null) {
            return;
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        releaseDeliveryAgent(order);
        notifyOrderParticipants(order);
    }
    
    public void assignDeliveryAgent(Order order) {
        if (order == null) {
            return;
        }

        deliveryAgentStrategy.findAgent(order, deliveryAgents).ifPresent(agent -> {
            order.assignDeliveryAgent(agent);
            order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
            notifyOrderParticipants(order);
        });
    }

    private boolean isValidOrder(User user, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        if (user == null || restaurant == null || orderLineItems == null || orderLineItems.isEmpty()) {
            System.out.println("Invalid order.");
            return false;
        }

        for (OrderLineItem item : orderLineItems) {
            if (item == null || item.getMenuItem() == null || item.getQuantity() <= 0) {
                System.out.println("Invalid order item.");
                return false;
            }

            if (!restaurant.getMenu().getMenuItems().contains(item.getMenuItem())) {
                System.out.println("Item " + item.getMenuItem().getName() + " is not available in this restaurant.");
                return false;
            }

            if (!item.getMenuItem().isAvailable()) {
                System.out.println("Item " + item.getMenuItem().getName() + " is currently unavailable.");
                return false;
            }
        }

        return true;
    }

    private void notifyOrderParticipants(Order order) {
        order.getUser().update(order);
        order.getRestaurant().update(order);
        if (order.getDeliveryAgent() != null) {
            order.getDeliveryAgent().update(order);
        }
    }

    private void releaseDeliveryAgent(Order order) {
        if (order.getDeliveryAgent() != null) {
            order.getDeliveryAgent().setAvailable(true);
        }
    }
    
}
