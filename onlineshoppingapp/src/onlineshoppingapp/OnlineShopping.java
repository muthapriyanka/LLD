package onlineshoppingapp;

import java.util.ArrayList;
import java.util.List;

public class OnlineShopping {
    private static OnlineShopping instance;
    List<Product> products;
    List<Order> orders;
    List<User> users;
    PaymentStrategy paymentStrategy;
    PaymentService paymentService;

    private OnlineShopping() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
        users = new ArrayList<>();
        paymentService = new PaymentService();
        
    }

    public static OnlineShopping getInstance() {
        if (instance == null) {
            instance = new OnlineShopping();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    
    public void addUser(User user) {
        users.add(user);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }


    public Order placeOrder(User user) {
        OrderService orderService = new OrderService();
        ShoppingCart cart = user.getAccount().getCart();
        if (cart.getItems().isEmpty()) {
            System.out.println("Cannot place an order with an empty cart.");
            return null;
        }

        boolean paymentSuccess = paymentService.processPayment(this.paymentStrategy, cart.calculateTotal());
        if (!paymentSuccess) {
            System.out.println("Payment failed. Please try again.");
            return null;
        }

        Order order = orderService.createOrder(user, cart);
        orders.add(order);
        return order;
    }

}
