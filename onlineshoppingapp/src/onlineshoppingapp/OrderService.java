package onlineshoppingapp;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static int orderIdCounter = 1;

    public Order createOrder(User user, ShoppingCart cart) {
        List<OrderItem> result = new ArrayList<>();
        for (CartItem cartItem : cart.getItems().values()) {
            result.add(new OrderItem(
                    cartItem.getProduct().getProductId(),
                    cartItem.getProduct().getName(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()));
        }

        Order order = new Order(orderIdCounter++, user, result);
        order.setOrderStatus(OrderStatus.PENDING);
        return order;
    }
}
