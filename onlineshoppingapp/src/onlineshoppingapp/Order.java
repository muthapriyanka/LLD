package onlineshoppingapp;

import java.util.List;

public class Order {
    int orderid;
    User user;
    List<OrderItem> orderItems;
    double totalAmount;
    OrderStatus orderStatus;

    public Order(int orderid, User user, List<OrderItem> orderItems) {
        this.orderid = orderid;
        this.user = user;
        this.orderItems = orderItems;
        this.totalAmount = calculateTotalAmount();
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public int getOrderId() {
        return orderid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    private double calculateTotalAmount() {
        return orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
