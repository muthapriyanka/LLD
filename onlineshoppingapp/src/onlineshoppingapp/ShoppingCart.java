package onlineshoppingapp;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        if (items.containsKey(product.getProductId())) {
            items.get(product.getProductId()).incrementQuantity(quantity);
        } else {
            items.put(product.getProductId(), new CartItem(product, quantity));
        }
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public Map<String, CartItem> getItems() { return Map.copyOf(items); }

    public double calculateTotal() {
        return items.values().stream().mapToDouble(CartItem::getPrice).sum();
    }

    public void clearCart() {
        items.clear();
    }
}
