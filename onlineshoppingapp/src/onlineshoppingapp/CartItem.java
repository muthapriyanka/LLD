package onlineshoppingapp;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void incrementQuantity(int amount) { this.quantity += amount; }
    public double getPrice() { return product.getPrice() * quantity; }
}


// An order‑line item is a single entry on an order – it represents one product (and its quantity/pricing) that’s been placed as part of a customer’s order.
// It’s not something in the shopping cart; the cart may have “cart items,” but once the order is created those become order line items.